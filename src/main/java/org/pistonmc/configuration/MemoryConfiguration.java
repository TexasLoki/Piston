package org.pistonmc.configuration;

import org.apache.commons.io.IOUtils;
import org.pistonmc.logging.Logging;

import java.io.IOException;
import java.io.InputStream;

public abstract class MemoryConfiguration extends ConfigurationSection {

    private static final long serialVersionUID = -4331253837613438633L;

    public abstract void load(String string);

    public void load(InputStream stream) {
        try {
            load(IOUtils.toString(stream));
        } catch(IOException ex) {
            Logging.getLogger().log(ex);
        }
    }

    public abstract String saveToString();

}
