package org.pistonmc.util.file;

import org.pistonmc.logging.Logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class TextFile {

	private File file;
	private boolean append;
    private List<String> lines;

    public TextFile(File file, boolean append) {
        this.file = file;
        this.append = append;
        load();
    }

    public TextFile(File file) {
        this(file, true);
    }

    public File getFile() {
        return file;
    }

    public boolean isAppend() {
        return append;
    }

    public List<String> getLines() {
        return lines;
    }

    public void addLine(String line) {
        String[] split = line.contains("\n") ? line.split("\n") : new String[]{line};
        for(String str : split) {
            if(!str.equals("")) {
                lines.add(str);
            }
        }
    }

    public void save() {
        file.delete();

        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file, append);
            PrintWriter printer = new PrintWriter(writer);
            for(String line : lines) {
                printer.printf("%s" + "%n" , line);
            }
            printer.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void load() {
        if(file.exists() && file.isDirectory()) {
            Logging.getLogger().warning("Could not load '" + file.getName() + "' because it was a directory");
            return;
        }

        if(append && file.exists()) {
            try {
                lines = Files.readAllLines(file.toPath());
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        } else {
            lines = new ArrayList<>();
        }
    }

}
