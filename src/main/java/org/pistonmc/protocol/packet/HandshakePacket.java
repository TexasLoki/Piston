package org.pistonmc.protocol.packet;

import org.pistonmc.exception.protocol.packet.PacketException;

import java.io.IOException;

public class HandshakePacket extends IncommingPacket {

    private int version;
    private String address;
    private int port;
    private ProtocolState state;

    public HandshakePacket() {
        super(ProtocolState.HANDSHAKE, 0x00);
    }

    public int getVersion() {
        return version;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public ProtocolState getState() {
        return state;
    }

    @Override
    public void read(UnreadPacket packet) throws PacketException, IOException {
        this.version = packet.getStream().readVarInt();
        this.address = packet.getStream().readString();
        this.port = packet.getStream().readUnsignedShort();
        this.state = ProtocolState.valueOf(packet.getStream().readVarInt());
    }

}
