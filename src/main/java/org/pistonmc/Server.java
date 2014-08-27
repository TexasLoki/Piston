package org.pistonmc;

import org.pistonmc.event.EventManager;
import org.pistonmc.logging.Logger;
import org.pistonmc.plugin.JavaPluginManager;
import org.pistonmc.plugin.protocol.ProtocolManager;

public interface Server {

    public Logger getLogger();

    public ProtocolManager getProtocolManager();

    public JavaPluginManager getPluginManager();

    public EventManager getEventManager();

    public void shutdown();

}
