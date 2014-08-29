package org.pistonmc.entity.builder;

public class BuilderArguments {

    private Object[] arguments;

    public BuilderArguments(Object... arguments) {
        this.arguments = arguments;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public Object get(int index) {
        return arguments[index];
    }

    public <T> T get(int index, Class<T> type) {
        return (T) get(index);
    }

}
