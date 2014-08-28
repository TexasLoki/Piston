package org.pistonmc.configuration;

import org.pistonmc.exception.configuration.IllegalConfigurationPathException;
import org.pistonmc.logging.Logging;
import org.pistonmc.util.OtherUtils;

import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class ConfigurationSection extends LinkedHashMap<String, Object> implements Configuration {

    private static final long serialVersionUID = 1054536830268098368L;

    public ConfigurationSection() {
        super();
    }

    public void set(String path, Object value) {
        String[] split = path.contains(".") ? path.split(".") : new String[]{path};

        ConfigurationSection previous = this;
        for(int i = 0; i < split.length; i++) {
            boolean last = i + 1 >= split.length;
            if(last) {
                previous.put(split[i], value);
            } else {
                ConfigurationSection section = getSection(previous.get(split[i]));
                if(section == null) {
                    section = new ConfigurationSection();
                    previous.set(split[i - 1], section);
                }

                previous = section;
            }
        }
    }

    public Object get(String path) {
        if(path.contains(".")) {
            String[] split = path.split(Pattern.quote("."));

            Object object = this;
            for(int i = 0; i < split.length; i++) {
                ConfigurationSection section = getSection(object);
                if(section == null) {
                    throw new IllegalConfigurationPathException(this, split, i + 1);
                }

                object = section.get(split[i]);
            }

            return object;
        } else {
            Object object = super.get(path);
            return object;
        }
    }

    public <T> List<String> getKeys(Class<T> cls) {
        List<String> keys = new ArrayList<>();
        for(Entry<String, Object> entry : entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            try {
                T val = (T) value;
                keys.add(key);
            } catch(ClassCastException ex) {
                /* ignored */
            }
        }

        return keys;
    }

    public List<String> getKeys(String path, Class<?> cls) {
        return getSection(path).getKeys(cls);
    }

    public <T> Map<String, T> getValues(Class<T> cls) {
        Map<String, T> values = new HashMap<>();
        for(Entry<String, Object> entry : entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            try {
                T val = (T) value;
                values.put(key, val);
            } catch(ClassCastException ex) {
                /* ignored */
            }
        }

        return values;
    }

    public <T> Map<String, T> getValues(String path, Class<T> cls) {
        return getSection(path).getValues(cls);
    }

    public String getString(String path) {
        try {
            return (String) get(path);
        } catch (Exception e) {
            return null;
        }
    }

    public void setString(String path, String value) {
        set(path, value);
    }

    public Boolean getBoolean(String path) {
        try {
            return (Boolean) get(path);
        } catch(Exception e) {
            return null;
        }
    }

    public void setBoolean(String path, boolean value) {
        set(path, value);
    }

    public Integer getInteger(String path) {
        try {
            return (int) get(path);
        } catch (Exception e) {
            return null;
        }
    }

    public void setInteger(String path, int value) {
        set(path, value);
    }

    public Double getDouble(String path) {
        try {
            return (Double) get(path);
        } catch (Exception e) {
            return null;
        }
    }

    public void setDouble(String path, double value) {
        set(path, value);
    }

    public Float getFloat(String path) {
        try {
            return (Float) get(path);
        } catch (Exception e) {
            return null;
        }
    }

    public void setFloat(String path, float value) {
        set(path, value);
    }

    public Long getLong(String path) {
        try {
            return (Long) get(path);
        } catch (Exception e) {
            return null;
        }
    }

    public void setLong(String path, long value) {
        set(path, value);
    }

    public List<String> getStringList(String path) {
        try {
            return (List<String>) get("path");
        } catch (Exception e) {
            return null;
        }
    }

    public void setStringList(String path, List<String> value) {
        set(path, value);
    }

    public List getList(String path) {
        try {
            return (List) get("path");
        } catch (Exception e) {
            return null;
        }
    }

    public void setList(String path, List value) {
        set(path, value);
    }

    public ConfigurationSection getSection(String path) {
        return getSection(get(path));
    }

    private ConfigurationSection getSection(Object object) {
        try {
            return object == null ? null : (ConfigurationSection) object;
        } catch(Exception ex) {
            return null;
        }
    }

    public void fromMap(Map<?, ?> map) {
        for(Entry<?, ?> entry : map.entrySet()) {
            String key = entry.getKey().toString();
            Object value = entry.getValue();

            if(value == null) {
                return;
            }

            if(value instanceof Map) {
                ConfigurationSection section = new ConfigurationSection();
                section.fromMap((Map<?, ?>) value);
                set(key, section);
            } else {
                set(key, value);
            }
        }
    }

    public Map<String, Object> asMap() {
        Map<String, Object> map = new LinkedHashMap<>();
        for(Entry<String, Object> entry : entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if(value instanceof Configuration) {
                Configuration configuration = (Configuration) value;
                map.put(key, configuration.asMap());
            } else {
                map.put(key, value);
            }
        }

        return map;
    }

}
