package com.finderfail.tagamefrontend;

import com.finderfail.tagamefrontend.client.OverlayRenderer;
import com.finderfail.tagamefrontend.common.ConfigHandler;
import com.finderfail.tagamefrontend.common.NetworkHandler;
import com.finderfail.tagamefrontend.server.ServerEventHandler;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "devindicator", name = "Dev Indicator", version = "1.0", acceptableRemoteVersions = "*")
public class DevIndicatorMod {
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigHandler.init(event.getModConfigurationDirectory());
        NetworkHandler.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if (event.getSide().isClient()) {
            new OverlayRenderer().register();
        } else {
            new ServerEventHandler().register();
        }
    }
}