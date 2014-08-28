package org.pistonmc.configuration;

public interface Configuration {

    public void set(String path, Object value);

    public Object get(String path);

    public ConfigurationSection getSection(String path);

}
