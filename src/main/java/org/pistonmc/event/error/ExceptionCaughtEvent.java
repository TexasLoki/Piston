package org.pistonmc.event.error;

import org.pistonmc.event.Event;

public class ExceptionCaughtEvent extends Event {

    private Exception exception;

    public ExceptionCaughtEvent(Exception e) {
        this.exception = e;
    }

    public Exception getException() {
        return exception;
    }

}
