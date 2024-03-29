package org.pistonmc.exception.protocol;

public class UnsupportedProtocolException extends ProtocolException {

    private static final long serialVersionUID = 396294565259799441L;

    private String reason;

    public UnsupportedProtocolException(int version, String reason) {
        super(version, "Protocol v" + version + " is not supported: " + reason);
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

}
