package org.pistonmc.event.command;

import org.pistonmc.commands.Command;
import org.pistonmc.event.Cancellable;
import org.pistonmc.event.Event;

public class CommandEvent extends Event {

    protected Command command;

    public CommandEvent(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}
