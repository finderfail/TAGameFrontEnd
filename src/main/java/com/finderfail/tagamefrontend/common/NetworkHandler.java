package com.finderfail.tagamefrontend.common;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {
    public static final SimpleNetworkWrapper INSTANCE = new SimpleNetworkWrapper("dev_channel");
    private static int ID = 0;

    public static void init() {
        INSTANCE.registerMessage(PacketSyncConfig.Handler.class, PacketSyncConfig.class, ID++, Side.CLIENT);
    }

    public static class PacketSyncConfig implements IMessage {
        private boolean isDev;
        private boolean isPlayTest;
        private String buildVersion;

        public PacketSyncConfig() {}
        public PacketSyncConfig(boolean isDev, boolean isPlayTest, String BuildVersion) {
            this.isDev = isDev;
            this.isPlayTest = isPlayTest;
            this.buildVersion = BuildVersion;
        }

        @Override
        public void fromBytes(ByteBuf buf) {
            isDev = buf.readBoolean();
            isPlayTest = buf.readBoolean();
            buildVersion = ByteBufUtils.readUTF8String(buf);
        }

        @Override
        public void toBytes(ByteBuf buf) {
            buf.writeBoolean(isDev);
            buf.writeBoolean(isPlayTest);
            ByteBufUtils.writeUTF8String(buf, buildVersion);
        }

        public static class Handler implements IMessageHandler<PacketSyncConfig, IMessage> {
            @Override
            public IMessage onMessage(PacketSyncConfig message, MessageContext ctx) {
                com.finderfail.tagamefrontend.client.ClientConfigData.update(message.isDev, message.isPlayTest, message.buildVersion);
                return null;
            }
        }
    }
}