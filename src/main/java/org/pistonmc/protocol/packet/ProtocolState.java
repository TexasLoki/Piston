package org.pistonmc.protocol.packet;

public enum ProtocolState {

    HANDSHAKE(-1),
    PLAY(0),
    STATUS(1),
    LOGIN(2);

    private int id;

    ProtocolState(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ProtocolState valueOf(int id) {
        for (ProtocolState state : values()) {
            if (state.getId() == id) {
                return state;
            }
        }

        return null;
    }

}
