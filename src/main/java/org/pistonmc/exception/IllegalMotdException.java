package org.pistonmc.exception;

public class IllegalMotdException extends RuntimeException {

    private static final long serialVersionUID = -1516870444890563473L;

    public IllegalMotdException(String message) {
        super(message);
    }

}
