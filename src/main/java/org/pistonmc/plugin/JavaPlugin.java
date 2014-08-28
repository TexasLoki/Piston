package org.pistonmc.plugin;

import org.pistonmc.configuration.file.Config;
import org.pistonmc.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLClassLoader;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class JavaPlugin implements Plugin {

    private URLClassLoader loader;
    private File file;

    private boolean enabled = false;
    private PluginDescription description;
    private Logger logger;

    private File dataFolder;
    private Config config;

    public PluginDescription getDescription() {
        return description;
    }

    public Logger getLogger() {
        return logger;
    }

    public File getDataFolder() {
        return dataFolder;
    }

    public Config getConfig() {
        return config;
    }

    public void onLoad() {

    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        boolean update = this.enabled != enabled;
        if(!update) {
            getLogger().info(getDescription().getName() + " is already " + (enabled ? "enabled" : "disabled"));
            return;
        }

        if(enabled) {
            getLogger().info("Enabling " + getDescription().getName() + " v" + getDescription().getVersion());
            try {
                onEnable();
                this.enabled = true;
            } catch(Exception ex) {
                ex.printStackTrace();
                logger.warning("An error occurred while enabling " + getDescription().getName() + ", is it up to date?");
            }
        } else {
            getLogger().info("Disabling " + getDescription().getName() + " v" + getDescription().getVersion());
            try {
                this.enabled = false;
                onDisable();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public InputStream getResource(String name) {
        try {
            JarFile jar = new JarFile(file, true);
            ZipEntry entry = jar.getEntry(name);
            return jar.getInputStream(entry);
        } catch(IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void reloadConfig() {
        config.reload();
    }

    public void saveConfig() {
        config.save();
    }

}
