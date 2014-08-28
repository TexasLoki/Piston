package org.pistonmc;

import org.pistonmc.event.EventManager;
import org.pistonmc.logging.Logger;
import org.pistonmc.plugin.JavaPluginManager;
import org.pistonmc.plugin.protocol.ProtocolManager;

import java.io.File;

public class Piston {

    private static Server server;
    private static File jar;

    public static Server getServer() {
        return server;
    }

    public static File getServerJar() {
        return jar;
    }

    public static Logger getLogger() {
        return server.getLogger();
    }

    public static ProtocolManager getProtocolManager() {
        return server.getProtocolManager();
    }

    public static JavaPluginManager getPluginManager() {
        return server.getPluginManager();
    }

    public static EventManager getEventManager() {
        return server.getEventManager();
    }

    public static void shutdown() {
        server.shutdown();
    }

}
