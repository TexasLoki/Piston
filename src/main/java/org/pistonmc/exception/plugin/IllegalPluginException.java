package org.pistonmc.exception.plugin;

import org.pistonmc.plugin.PluginDescription;

public class IllegalPluginException extends PluginException {

    private static final long serialVersionUID = 4977717817542343612L;

    public IllegalPluginException(String name, String version, String message) {
        super(name, version, message);
    }

    public IllegalPluginException(PluginDescription description, String message) {
        super(description, message);
    }

}
