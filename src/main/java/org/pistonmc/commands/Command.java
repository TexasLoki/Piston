package org.pistonmc.commands;

public interface Command {

    public String[] getAliases();
    public String getDescription();
    public String getUsage();
    public void onExecute(String[] args, CommandSender sender);

}
