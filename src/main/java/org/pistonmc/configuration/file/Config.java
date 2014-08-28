package org.pistonmc.configuration.file;

import org.pistonmc.exception.configuration.InvalidConfigurationException;
import org.pistonmc.logging.Logger;
import org.pistonmc.logging.Logging;
import org.pistonmc.plugin.JavaPlugin;
import org.pistonmc.util.reflection.SimpleObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class Config extends YamlConfiguration {

    private static final long serialVersionUID = 8708076338509087808L;

    private File jar;
    private JavaPlugin plugin;
	private File file;
	private String def;

    protected Config(JavaPlugin plugin, File file, String def) {
        this(new SimpleObject(plugin).field("file").value(File.class), file, def);
        this.plugin = plugin;
    }

	protected Config(File jar, File file, String def) {
		this.jar = jar;
		this.file = file;
		this.def = def;
	}

    public Logger getLogger() {
        return plugin != null ? plugin.getLogger() : Logging.getLogger();
    }

	public FileConfiguration getConfig() {
		return this;
	}

	public void reload() {
        // FileNotFoundException fnfe = null;
		try {
			load(file);
            Logging.getLogger().debug("Loaded from " + file);
			return;
		} catch(FileNotFoundException ex) {
            // fnfe = ex;
            /* ignored */
		} catch(IOException | InvalidConfigurationException ex) {
            getLogger().log("Cannot load " + file, ex);
		}

		// Look for defaults in the jar
        def = def != null ? def : file.getName();
		InputStream defConfigStream = getResource(jar, def);
		if(defConfigStream != null) {
            getLogger().debug("Loading defaults from " + def);
            load(defConfigStream);
            getLogger().debug("Loaded from " + defConfigStream);
            return;
		}

        /*
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
        */
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

    public static Config load(File jar, File file, String def) {
        Config config = new Config(jar, file, def);
        config.reload();
        return config;
    }

    public static Config load(File jar, File file) {
        return load(jar, file, file.getName());
    }

    public static Config load(File jar, String name, String def) {
        return load(jar, new File(name), def);
    }

    public static Config load(File jar, String name) {
        return load(jar, new File(name), name);
    }

    private static InputStream getResource(File file, String name) {
        try {
            JarFile jar = new JarFile(file, true);
            ZipEntry entry = jar.getEntry(name);
            return jar.getInputStream(entry);
        } catch(Exception ex) {
            return null;
        }
    }

}
