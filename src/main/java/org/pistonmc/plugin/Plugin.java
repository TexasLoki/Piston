package org.pistonmc.plugin;

import org.pistonmc.logging.Logger;

import java.io.File;

public interface Plugin {

    public PluginDescription getDescription();

    public Logger getLogger();

    public File getDataFolder();

    public void onLoad();

    public void onEnable();

    public void onDisable();

    public boolean isEnabled();

    public void setEnabled(boolean enabled);

}
