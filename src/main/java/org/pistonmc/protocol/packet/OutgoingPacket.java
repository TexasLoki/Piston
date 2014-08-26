package org.pistonmc.protocol.packet;

import org.pistonmc.exception.protocol.packet.IllegalPacketDestinationException;
import org.pistonmc.exception.protocol.packet.PacketException;

public abstract class OutgoingPacket extends Packet {

    protected OutgoingPacket(ProtocolState state, int id) {
        super(state, id);
    }

    @Override
    public void read(UnreadPacket packet) throws PacketException {
        throw new IllegalPacketDestinationException(this, PacketDestination.SERVER);
    }

}
