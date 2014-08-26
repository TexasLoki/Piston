package org.pistonmc.exception.protocol;

public class ProtocolNotFoundException extends ProtocolException {

    private static final long serialVersionUID = 396294565259799441L;

    public ProtocolNotFoundException(int version) {
        super(version, "Could not find Protocol v" + version);
    }

}
