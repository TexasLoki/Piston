package org.pistonmc.commands.server;

import org.pistonmc.commands.Command;
import org.pistonmc.commands.CommandSender;

public class TestCommand implements Command {

    @Override
    public String[] getAliases() {
        return new String[]{"test"};
    }

    @Override
    public String getDescription() {
        return "A test command";
    }

    @Override
    public String getUsage() {
        return "[arguments]";
    }

    @Override
    public void onExecute(String[] args, CommandSender sender) {
        for (String s : args) {
            sender.sendMessage(s);
        }
    }

}
