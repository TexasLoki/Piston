package org.pistonmc.configuration.file;

import org.apache.commons.io.IOUtils;
import org.pistonmc.configuration.ConfigurationSection;
import org.pistonmc.configuration.MemoryConfiguration;
import org.pistonmc.logging.Logging;
import org.pistonmc.util.file.TextFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class FileConfiguration extends MemoryConfiguration {

    private static final long serialVersionUID = 3599464546882265306L;

    public void load(File file) throws IOException {
        FileInputStream input = new FileInputStream(file);
        String string = IOUtils.toString(input);
        load(string);
    }

    public void save(File file) throws IOException {
        TextFile text = new TextFile(file, false);
        String string = saveToString();
        text.addLine(string);
        text.save();
    }

}
