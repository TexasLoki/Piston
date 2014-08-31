package org.pistonmc.logging;

import java.util.ArrayList;
import java.util.List;

public class LogWriter {

    private List<Runnable> runnables;

    public LogWriter() {
        this.runnables = new ArrayList<>();
    }

    public List<Runnable> getRunnables() {
        return runnables;
    }

    public void write(final String message) {
        runnables.add(new Runnable() {
            @Override
            public void run() {
                System.out.println(message);
            }
        });
    }

}
