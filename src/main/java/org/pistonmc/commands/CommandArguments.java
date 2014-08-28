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
        if (array[position].equalsIgnoreCase("true") || array[position].equalsIgnoreCase("yes")) {
            return true;
        } else if (array[position].equalsIgnoreCase("false") || array[position].equalsIgnoreCase("no")) {
            return false;
        } else {
            throw new IllegalArgumentException("Boolean must either be true/false/yes/no");
        }
    }

    public String[] getArguments() {
        return array;
    }

    public String getCommand() {
        return command;
    }

}
