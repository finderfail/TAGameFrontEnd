package com.finderfail.tagamefrontend.server;

import com.finderfail.tagamefrontend.common.ConfigHandler;
import com.finderfail.tagamefrontend.common.NetworkHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class ServerEventHandler {
    public void register() {
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        // Отправка конфига игроку при входе
        EntityPlayerMP player = (EntityPlayerMP) event.player;
        NetworkHandler.INSTANCE.sendTo(
                new NetworkHandler.PacketSyncConfig(
                        ConfigHandler.IsDevelopment,
                        ConfigHandler.IsPlayTest,
                        ConfigHandler.BuildVersion
                ),
                player
        );
    }

    // В ServerEventHandler.java
    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent event) {
        if (event.getModID().equals("devindicator")) {
            // Отправить обновление всем игрокам
            for (EntityPlayerMP player : FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers()) {
                NetworkHandler.INSTANCE.sendTo(
                        new NetworkHandler.PacketSyncConfig(ConfigHandler.IsDevelopment, ConfigHandler.IsPlayTest, ConfigHandler.BuildVersion),
                        player
                );
            }
        }
    }
}
