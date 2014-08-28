package org.pistonmc.commands;

public interface Command {

    public String[] getAliases();

    public String getDescription();

    public String getUsage();

    public void onExecute(CommandArguments args, CommandSender sender);

}
