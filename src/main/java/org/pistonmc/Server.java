package org.pistonmc;

import org.pistonmc.commands.CommandRegistry;
import org.pistonmc.configuration.file.Config;
import org.pistonmc.entity.builder.BuilderRegistry;
import org.pistonmc.event.EventManager;
import org.pistonmc.logging.Logger;
import org.pistonmc.plugin.JavaPluginManager;
import org.pistonmc.plugin.protocol.ProtocolManager;
import org.pistonmc.world.WorldManager;

public interface Server {

    public Logger getLogger();

    public Config getConfig();

    public EventManager getEventManager();

    public ProtocolManager getProtocolManager();

    public JavaPluginManager getPluginManager();

    public WorldManager getWorldManager();

    public CommandRegistry getCommandRegistry();

    public BuilderRegistry getBuilderRegistry();

    public void shutdown();

}
