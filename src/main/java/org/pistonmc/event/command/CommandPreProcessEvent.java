package org.pistonmc.event.command;

import org.pistonmc.commands.Command;
import org.pistonmc.commands.CommandArguments;
import org.pistonmc.commands.CommandSender;
import org.pistonmc.event.Cancellable;

public class CommandPreProcessEvent extends CommandEvent implements Cancellable {

    private boolean cancelled;
    private CommandArguments arguments;
    private CommandSender sender;

    public CommandPreProcessEvent(Command command, CommandArguments arguments, CommandSender sender) {
        super(command);
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public CommandArguments getArguments() {
        return arguments;
    }

    public CommandSender getSender() {
        return sender;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

}
