package com.finderfail.tagamefrontend.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class OverlayRenderer {
    public void register() {
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Text event) {
        if (ClientConfigData.isDevMode()) {
            renderText("Developer Mod", 0xFF0000, 20, 30);
            renderText("Photo and video recording is forbidden", 0xFF0000, 20, 40);
            renderText("Non-disclosure agreement", 0xFF0000, 20, 50);

        }
        if (ClientConfigData.isPlayTestMode()) {
            renderText("PlayTest Unmoderated", 0xFFA500, 20, 30);
            renderText("Non-disclosure agreement", 0xFFA500, 20, 40);
        }
        String version = ClientConfigData.getBuildVersion();
        if (!version.isEmpty()) {
            String cleanVersion = version.replace("\"", "");
            renderText(cleanVersion, 0xADD8E6, 5, 5);
        }
    }

    private void renderText(String text, int color, int x, int y) {
        Minecraft mc = Minecraft.getMinecraft();
        FontRenderer fr = mc.fontRenderer;
        ScaledResolution res = new ScaledResolution(mc);

        fr.drawStringWithShadow(text, x, y, color);
    }
}