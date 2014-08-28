package org.pistonmc.commands;

import java.util.Collection;

public interface CommandRegistry {

    public void execute(String[] args, CommandSender sender);

    public void addCommand(Command c);

    public Collection<Command> getCommands();

}
