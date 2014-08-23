package org.pistonmc.plugin;

import org.pistonmc.logging.Logger;

import java.io.File;

public class JavaPluginManager extends PluginManager<JavaPlugin> {

    public JavaPluginManager(Logger logger, File folder) {
        super(logger, folder, "plugin.json");
    }

}
