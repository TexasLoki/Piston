package org.pistonmc.exception.configuration;

import org.pistonmc.configuration.Configuration;

public class ConfigurationException extends RuntimeException {

    private static final long serialVersionUID = 4621936100133743554L;

    private Configuration configuration;

    public ConfigurationException(Configuration configuration, String message) {
        super(message);
        this.configuration = configuration;
    }

    public ConfigurationException(Configuration configuration, Throwable cause) {
        super(cause);
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

}
