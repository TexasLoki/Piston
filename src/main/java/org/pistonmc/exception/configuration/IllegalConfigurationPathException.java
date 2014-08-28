package org.pistonmc.exception.configuration;

import org.pistonmc.configuration.Configuration;
import org.pistonmc.util.OtherUtils;

public class IllegalConfigurationPathException extends ConfigurationException {

    private static final long serialVersionUID = -4394260295900968989L;

    public IllegalConfigurationPathException(Configuration configuration, String path) {
        super(configuration, path + " is not a valid configuration path");
    }

    public IllegalConfigurationPathException(Configuration configuration, String[] path, int display) {
        super(configuration, OtherUtils.join(path, ".", display) + " is not a valid configuration path");
    }

}
