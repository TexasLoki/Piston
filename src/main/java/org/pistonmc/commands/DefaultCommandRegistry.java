package org.pistonmc.commands;

import org.pistonmc.ChatColor;
import org.pistonmc.Piston;
import org.pistonmc.commands.server.HelpCommand;
import org.pistonmc.commands.server.TestCommand;

import java.util.ArrayList;
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
        if (args.length < 1) return;
        Command command = null;
        for (Command c : commandList) {
            for (String s : c.getAliases()) {
                if (s.equalsIgnoreCase(args[0].toLowerCase())) {
                    command = c;
                }
            }
        }
        if (command == null) {
            sender.sendMessage(ChatColor.RED + "Unknown command. Use \"/help\" for a list of commands.");
        } else {
            try {
                command.onExecute(args, sender);
                // TODO: Replace args with custom args object.
            } catch (Exception e) {
                sender.sendMessage(ChatColor.RED + "There was an error while processing your command.");
                Piston.getLogger().debug(e);
            }
        }
    }

    @Override
    public void addCommand(Command c) {
        commandList.add(c);
    }

    @Override
    public Collection<Command> getCommands() {
        return commandList;
    }

}