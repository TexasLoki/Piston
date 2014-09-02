package org.pistonmc.scheduler;

import org.pistonmc.plugin.Plugin;

public interface PistonScheduler {

    public PistonTask run(Plugin plugin, Runnable runnable);

    public PistonTask runAsync(Plugin plugin, Runnable runnable);

    public PistonTask delay(Plugin plugin, Runnable runnable, long delay);

    public PistonTask delay(Plugin plugin, Runnable runnable, Duration duration, long delay);

    public PistonTask delayAsync(Plugin plugin, Runnable runnable, long delay);

    public PistonTask delayAsync(Plugin plugin, Runnable runnable, Duration duration, long delay);

    public PistonTask repeat(Plugin plugin, Runnable runnable, long delay, long period, Long cancel);

    public PistonTask repeat(Plugin plugin, Runnable runnable, Duration duration, long delay, long period, Long cancel);

    public PistonTask repeatAsync(Plugin plugin, Runnable runnable, long delay, long period, Long cancel);

    public PistonTask repeatAsync(Plugin plugin, Runnable runnable, Duration duration, long delay, long period, Long cancel);

    public PistonTask repeat(Plugin plugin, Runnable runnable, long delay, long period);

    public PistonTask repeat(Plugin plugin, Runnable runnable, Duration duration, long delay, long period);

    public PistonTask repeatAsync(Plugin plugin, Runnable runnable, long delay, long period);

    public PistonTask repeatAsync(Plugin plugin, Runnable runnable, Duration duration, long delay, long period);

    public PistonTask repeat(Plugin plugin, Runnable runnable, long period);

    public PistonTask repeat(Plugin plugin, Runnable runnable, Duration duration, long period);

    public PistonTask repeatAsync(Plugin plugin, Runnable runnable, long period);

    public PistonTask repeatAsync(Plugin plugin, Runnable runnable, Duration duration, long period);

    public void cancel(PistonTask task);

    public void cancel(int id);

    public void cancel(Plugin plugin);

}
