package org.pistonmc.configuration.file;

import org.pistonmc.exception.configuration.InvalidConfigurationException;
import org.pistonmc.logging.Logging;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

import java.util.Map;

public class YamlConfiguration extends FileConfiguration {

    private static final long serialVersionUID = 4379037675003929039L;

    private Yaml yaml;

    public YamlConfiguration() {
        this.yaml = new Yaml();
    }

    @Override
    public void load(String string) {
        if(string == null) {
            throw new InvalidConfigurationException(this, "Configuration string cannot be null");
        }

        Logging.getLogger().debug("Loading \n" + string);
        Map<?, ?> input;
        try {
            input = (Map<?, ?>) yaml.load(string);
        } catch(YAMLException ex) {
            throw new InvalidConfigurationException(this, ex);
        } catch(ClassCastException ex) {
            throw new InvalidConfigurationException(this, "Top level is not a Map");
        }

        if(input == null) {
            return;
        }

        fromMap(input);
    }

    @Override
    public String saveToString() {
        Map<String, Object> map = asMap();
        Logging.getLogger().info("\n" + map);
        return yaml.dump(map);
    }

}
