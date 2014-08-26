package org.pistonmc.exception.protocol.packet;

import org.pistonmc.protocol.packet.Packet;
import org.pistonmc.protocol.packet.ProtocolState;

public class PacketException extends Exception {

    private ProtocolState state;
    private int id;
    private String packet;

    public PacketException(String packet, ProtocolState state, int id, String message) {
        super("Error with " + packet + ": " + message);
        this.state = state;
        this.id = id;
        this.packet = packet;
    }

    public PacketException(ProtocolState state, int id, String message) {
        this(state.name() + " Packet #" + id, state, id, message);
    }

    public PacketException(Packet packet, String message) {
        this(packet.getClass().getSimpleName(), packet.getState(), packet.getId(), message);
    }

    public ProtocolState getState() {
        return state;
    }

    public int getId() {
        return id;
    }

    public String getPacket() {
        return packet;
    }

}
