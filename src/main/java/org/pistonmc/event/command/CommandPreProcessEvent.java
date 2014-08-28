package org.pistonmc.event.command;

import org.pistonmc.commands.Command;
import org.pistonmc.event.Cancellable;

public class CommandPreProcessEvent extends CommandEvent implements Cancellable {

    private boolean cancelled;

    public CommandPreProcessEvent(Command command) {
        super(command);
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

}
