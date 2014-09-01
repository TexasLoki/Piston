package org.pistonmc;

import org.pistonmc.commands.CommandRegistry;
import org.pistonmc.configuration.file.Config;
import org.pistonmc.entity.builder.BuilderRegistry;
import org.pistonmc.event.EventManager;
import org.pistonmc.logging.Logger;
import org.pistonmc.plugin.JavaPluginManager;
import org.pistonmc.plugin.protocol.ProtocolManager;
import org.pistonmc.scheduler.PistonScheduler;
import org.pistonmc.world.WorldManager;

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

    public static Config getConfig() {
        return server.getConfig();
    }

    public static PistonScheduler getScheduler() {
        return server.getScheduler();
    }

    public static EventManager getEventManager() {
        return server.getEventManager();
    }

    public static ProtocolManager getProtocolManager() {
        return server.getProtocolManager();
    }

    public static JavaPluginManager getPluginManager() {
        return server.getPluginManager();
    }

    public static WorldManager getWorldManager() {
        return server.getWorldManager();
    }

    public static CommandRegistry getCommandRegistry() {
        return server.getCommandRegistry();
    }

    public static BuilderRegistry getBuilderRegistry() {
        return server.getBuilderRegistry();
    }

    public static void shutdown() {
        server.shutdown();
    }

    public static void shutdown(String message) {
        server.shutdown(message);
    }

}
