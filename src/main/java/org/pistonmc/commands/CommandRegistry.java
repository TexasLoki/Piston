package org.pistonmc.commands;

import java.util.Collection;

public interface CommandRegistry {

    public void execute(String[] args, CommandSender sender);

    public Command addCommand(Command c);

    public Command addCommand(Class<?> cls);

    public Command build(Class<?> cls);

    public Collection<Command> getCommands();

}
