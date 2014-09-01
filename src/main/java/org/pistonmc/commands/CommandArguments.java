package org.pistonmc.commands;

import org.pistonmc.util.OtherUtils;

public class CommandArguments {

    private static String defJoin = " ";

    private String command;
    private String[] array;

    public CommandArguments(String command, String[] array) {
        this.command = command;
        this.array = array;
    }

    public int length() {
        return array.length;
    }

    public String getJoinedString(int start, int end, String join) {
        if(start >= array.length) {
            throw new IndexOutOfBoundsException("Start point (" + start + ") is exceeds array end point");
        }

        if(end >= array.length) {
            throw new IndexOutOfBoundsException("End point (" + end + ") is exceeds array end point");
        }

        int i2 = 0;
        String[] strings = new String[array.length - start];
        for(int i = start; i < array.length; i++, i2++) {
            strings[i2] = array[i];
        }

        return OtherUtils.join(strings, join);
    }

    public String getJoinedString(int start, int end) {
        return getJoinedString(start, end, defJoin);
    }

    public String getJoinedString(int start, String join) {
        return getJoinedString(start, array.length - 1, join);
    }

    public String getJoinedString(int start) {
        return getJoinedString(start, defJoin);
    }

    public String getJoinedString(String join) {
        return getJoinedString(0, join);
    }

    public String getJoinedString() {
        return getJoinedString(0, defJoin);
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
        String[] copy = new String[array.length];
        System.arraycopy(array, 0, copy, 0, array.length);
        return copy;
    }

    public String getCommand() {
        return command;
    }

}
