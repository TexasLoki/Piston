package org.pistonmc.commands;

import org.pistonmc.ChatColor;
import org.pistonmc.Piston;
import org.pistonmc.commands.server.HelpCommand;
import org.pistonmc.commands.server.TestCommand;
import org.pistonmc.event.command.CommandPreProcessEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class DefaultCommandRegistry implements CommandRegistry {

    private List<Command> commandList;

    public DefaultCommandRegistry() {
        commandList = new ArrayList<>();

        commandList.add(new HelpCommand());
        commandList.add(new TestCommand());
    }

    @Override
    public void execute(String[] args, CommandSender sender) {
        if(args.length < 1) {
            return;
        }

        Command command = null;
        for(Command cmd : commandList) {
            for(String s : cmd.getAliases()) {
                if(s.equalsIgnoreCase(args[0])) {
                    command = cmd;
                }
            }
        }

        CommandPreProcessEvent event = new CommandPreProcessEvent(command);
        Piston.getEventManager().call(event);
        command = event.getCommand();

        if(command == null) {
            sender.sendMessage(ChatColor.RED + "Unknown command. Use \"/help\" for a list of commands.");
        } else {
            try {
                command.onExecute(new CommandArguments(args[0], Arrays.copyOfRange(args, 1, args.length)), sender);
            } catch (Exception e) {
                sender.sendMessage(ChatColor.RED + "There was an error while processing your command.");
                Piston.getLogger().debug(e);
            }
        }
    }

    @Override
    public Command addCommand(Command command) {
        commandList.add(command);
        return command;
    }

    @Override
    public Command addCommand(Class<?> cls) {
        return null;
    }

    @Override
    public Command build(Class<?> cls) {
        return null;
    }

    @Override
    public Collection<Command> getCommands() {
        return commandList;
    }

}