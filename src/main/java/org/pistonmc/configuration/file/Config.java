package org.pistonmc.configuration.file;

import org.pistonmc.exception.configuration.InvalidConfigurationException;
import org.pistonmc.logging.Logging;
import org.pistonmc.plugin.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

public class Config extends YamlConfiguration {

    private static final long serialVersionUID = 8708076338509087808L;

    private JavaPlugin plugin;
	private File file;
	private String def;

	protected Config(JavaPlugin plugin, File file, String def) {
		this.plugin = plugin;
		this.file = file;
		this.def = def;
	}

	public FileConfiguration getConfig() {
		return this;
	}

	public void reload() {
        FileNotFoundException fnfe = null;
		try {
			load(file);
            Logging.getLogger().debug("Loaded from " + file);
			return;
		} catch(FileNotFoundException ex) {
            fnfe = ex;
		} catch(IOException | InvalidConfigurationException ex) {
            plugin.getLogger().log("Cannot load " + file, ex);
		}

		plugin.getLogger().debug("Loading defaults from " + def);

		// Look for defaults in the jar
		InputStream defConfigStream = plugin.getResource(def);
		if(defConfigStream != null) {
            load(defConfigStream);
            plugin.getLogger().debug("Loaded from " + defConfigStream);
            return;
		}

        if(fnfe != null) {
            file.getParentFile().mkdirs();
            try {
                file.delete();
                boolean created = file.createNewFile();
                if(!created) {
                    Logging.getLogger().log(Level.SEVERE, "Cannot load " + file + " because an empty file could not be created");
                    return;
                }

                reload();
            } catch(IOException e) {
                plugin.getLogger().log(Level.SEVERE, "Cannot load " + file, fnfe);
            }
        }
	}

	public void save() {
		if(file == null) {
			return;
		}

		try {
			file.getParentFile().mkdirs();
			save(file);
		} catch(IOException ex) {
			plugin.getLogger().severe("Could not save config to " + file + " " + ex.getMessage());
		}
	}

	public static Config load(JavaPlugin plugin, File file, String def) {
        Config config = new Config(plugin, file, def);
        config.reload();
        return config;
	}

	public static Config load(JavaPlugin plugin, File file) {
		return load(plugin, file, file.getName());
	}

	public static Config load(JavaPlugin plugin, String name, String def) {
		return load(plugin, new File(plugin.getDataFolder(), name), def);
	}

	public static Config load(JavaPlugin plugin, String name) {
		return load(plugin, new File(plugin.getDataFolder(), name), name);
	}

}
