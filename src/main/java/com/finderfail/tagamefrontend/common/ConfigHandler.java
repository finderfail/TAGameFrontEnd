package com.finderfail.tagamefrontend.common;


import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

@Config(modid = "devindicator", category = "server")
public class ConfigHandler {
    @Config.Comment("Enable development mode")
    public static boolean IsDevelopment = false;

    @Config.Comment("Enable playtest mode")
    public static boolean IsPlayTest = false;

    @Config.Comment("Build version (e.g., 1.0.0)")
    public static String BuildVersion = "";

    public static void init(File configDir) {
        ConfigManager.sync("devindicator", Config.Type.INSTANCE);
    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent event) {
        if (event.getModID().equals("devindicator")) {
            ConfigManager.sync("devindicator", Config.Type.INSTANCE);
        }
    }
}
