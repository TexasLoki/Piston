package org.pistonmc.protocol.packet;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.stream.PacketOutputStream;
import org.pistonmc.util.ClassUtils;

import java.io.IOException;

public abstract class Packet {

    private ProtocolState state;
    private int id;

    protected Packet(ProtocolState state, int id) {
        this.state = state;
        this.id = id;
    }

    public ProtocolState getState() {
        return state;
    }

    public int getId() {
        return id;
    }

    public abstract void read(UnreadPacket packet) throws PacketException, IOException;

    public abstract void write(PacketOutputStream stream) throws PacketException, IOException;

    @Override
    public String toString() {
        return ClassUtils.build(this);
    }

}
