package org.pistonmc.commands;

public class CommandArguments {

    private String[] array;

    public CommandArguments(String[] array) {
        this.array = array;
    }

    public int length() {
        return array.length-1;
    }

    public String getString(int position) {
        return array[position+1];
    }

    public int getInteger(int position) {
        return Integer.parseInt(array[position+1]);
    }

    public boolean getBoolean(int position) {
        return array[position+1].equalsIgnoreCase("true") || array[position+1].equalsIgnoreCase("yes");
    }

    public String[] getArguments() {
        String[] resp = new String[length()];
        for (int i = 1; i < array.length-1; i++) {
            resp[i-1] = array[i];
        }
        return resp;
    }

    public String getCommand() {
        return array[0];
    }

}
