package org.pistonmc.scheduler;

import org.pistonmc.plugin.JavaPlugin;

public class Schedule {

	protected JavaPlugin plugin;
	protected PistonTask task;
	protected boolean running;
	private Runnable stopRunnable = new Runnable() {

		@Override
		public void run() {
			running = false;
		}

	};
	protected Runnable run;
	protected boolean async;

	public Schedule(JavaPlugin plugin, Runnable run, boolean async) {
		this.plugin = plugin;
		this.run = run;
		this.running = false;
		this.async = async;
	}

	public boolean isRunning() {
		return running || task != null;
	}

	public Runnable getRunnable() {
		return run;
	}

	public boolean delay(long delay) {
		return delay(Duration.TICKS, delay);
	}

	public boolean delay(Duration duration, long delay) {
		if(running) {
			return false;
		}

		if(async) {
			this.task = plugin.getServer().getScheduler().delayAsync(plugin, run, duration, delay);
		} else {
            this.task = plugin.getServer().getScheduler().delay(plugin, run, duration, delay);
		}

		this.running = true;
		plugin.getServer().getScheduler().delay(plugin, stopRunnable, duration, delay);
		return true;
	}

	public boolean repeat(long period) {
		return repeat(Duration.TICKS, 0, period);
	}

	public boolean repeat(Duration duration, long period) {
		return repeat(0, duration.getTicks(period));
	}

	public boolean repeat(long delay, long period) {
		return repeat(Duration.TICKS, delay, period, -1);
	}

	public boolean repeat(Duration duration, long delay, long period) {
		return repeat(duration.getTicks(delay), duration.getTicks(period), -1);
	}

	public boolean repeat(long delay, long period, long cancel) {
		return repeat(Duration.TICKS, delay, period, cancel);
	}

	public boolean repeat(Duration duration, long delay, long period, long cancel) {
		if(running) {
			return false;
		}

		if(async) {
            this.task = plugin.getServer().getScheduler().repeatAsync(plugin, run, duration, delay, period);
		} else {
            this.task = plugin.getServer().getScheduler().repeat(plugin, run, duration, delay, period);
		}

		this.running = true;

		if(cancel > 0) {
            plugin.getServer().getScheduler().delay(plugin, run, duration, cancel);
		}

		return true;
	}

	public boolean stop() {
		if(!running) {
			return false;
		}

		plugin.getServer().getScheduler().cancel(task);
		this.running = false;
		this.task = null;
		return true;
	}

	public void run() {
		if(async) {
			plugin.getServer().getScheduler().runAsync(plugin, run);
		} else {
			plugin.getServer().getScheduler().run(plugin, run);
		}
	}

}
