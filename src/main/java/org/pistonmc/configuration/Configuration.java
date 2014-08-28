package org.pistonmc.configuration;

import java.util.List;
import java.util.Map;

public interface Configuration {

    public void set(String path, Object value);

    public Object get(String path);

    public ConfigurationSection getSection(String path);

    public <T> List<String> getKeys(Class<T> cls);

    public List<String> getKeys(String path, Class<?> cls);

    public <T> Map<String, T> getValues(Class<T> cls);

    public <T> Map<String, T> getValues(String path, Class<T> cls);

    public String getString(String path);

    public void setString(String path, String value);

    public Boolean getBoolean(String path);

    public void setBoolean(String path, boolean value);

    public Integer getInteger(String path);

    public void setInteger(String path, int value);

    public Double getDouble(String path);

    public void setDouble(String path, double value);

    public Float getFloat(String path);

    public void setFloat(String path, float value);

    public Long getLong(String path);

    public void setLong(String path, long value);

    public List<String> getStringList(String path);

    public void setStringList(String path, List<String> value);

    public List getList(String path);

    public void setList(String path, List value);

    public void fromMap(Map<?, ?> map);

    public Map<String, Object> asMap();

}
