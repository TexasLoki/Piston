package org.pistonmc.exception.protocol;

public class ProtocolException extends Exception {

    private int protocol;

    public ProtocolException(int protocol, String message) {
        super(message);
        this.protocol = protocol;
    }

    public int getProtocol() {
        return protocol;
    }

}
