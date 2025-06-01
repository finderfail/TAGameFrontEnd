package com.finderfail.tagamefrontend.client;


public class ClientConfigData {
    private static boolean isDevMode = false;
    private static boolean isPlayTestMode = false;
    private static String buildVersion = "";

    public static void update(boolean isDev, boolean isPlayTest, String version) {
        isDevMode = isDev;
        isPlayTestMode = isPlayTest;
        buildVersion = version;
    }

    public static boolean isDevMode() {
        return isDevMode;
    }

    public static boolean isPlayTestMode() {
        return isPlayTestMode;
    }

    public static String getBuildVersion() {
        return buildVersion;
    }
}