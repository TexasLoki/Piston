package org.pistonmc.commands;

public class CommandArguments {

    private String command;
    private String[] array;

    public CommandArguments(String command, String[] array) {
        this.command = command;
        this.array = array;
    }

    public int length() {
        return array.length;
    }

    public String getString(int position) {
        return array[position];
    }

    public int getInteger(int position) {
        return Integer.parseInt(array[position]);
    }

    public boolean getBoolean(int position) {
        return array[position].equalsIgnoreCase("true") || array[position + 1].equalsIgnoreCase("yes");
    }

    public String[] getArguments() {
        return array;
    }

    public String getCommand() {
        return command;
    }

}
