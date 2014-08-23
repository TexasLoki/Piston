package org.pistonmc;

import org.pistonmc.logging.Logger;
import org.pistonmc.plugin.PluginManager;
import org.pistonmc.plugin.protocol.ProtocolManager;

public interface Server {

    public Logger getLogger();

    public ProtocolManager getProtocolManager();

    public PluginManager getPluginManager();

    public void shutdown();

}
