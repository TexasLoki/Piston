package org.pistonmc.scheduler;

import org.pistonmc.plugin.Plugin;

public interface PistonTask {

    public boolean isSync();

    public Plugin getOwner();

    public Runnable getTask();

    public int getId();

    public long getDelay();

    public long getPeriod();

    public void run();

    public void cancel();

    public boolean isCancelled();

}
