package org.pistonmc.configuration;

import org.pistonmc.exception.configuration.IllegalConfigurationPathException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

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
                previous.set(split[i], value);
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
            String[] split = path.split(".");

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
            return super.get(path);
        }
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

    protected void fromMap(Map<?, ?> map) {
        for(Entry<?, ?> entry : map.entrySet()) {
            String key = entry.getKey().toString();
            Object value = entry.getValue();

            if(value instanceof Map) {
                ConfigurationSection section = new ConfigurationSection();
                section.fromMap((Map<?, ?>) value);
                set(key, section);
            } else {
                set(key, value);
            }
        }
    }

    protected Map<String, Object> asMap() {
        Map<String, Object> map = new LinkedHashMap<>();
        for(Entry<String, Object> entry : entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if(value instanceof ConfigurationSection) {
                ConfigurationSection section = (ConfigurationSection) value;
                map.put(key, section.asMap());
            } else {
                set(key, value);
            }
        }

        return map;
    }

}
