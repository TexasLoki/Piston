package org.pistonmc.exception.configuration;

import org.pistonmc.configuration.Configuration;

public class InvalidConfigurationException extends ConfigurationException {

    private static final long serialVersionUID = 7333196681494651339L;

    public InvalidConfigurationException(Configuration configuration, String message) {
        super(configuration, message);
    }

    public InvalidConfigurationException(Configuration configuration, Throwable cause) {
        super(configuration, cause);
    }

}
