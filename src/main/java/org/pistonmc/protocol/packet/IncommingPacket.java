package org.pistonmc.protocol.packet;

import org.pistonmc.exception.protocol.packet.IllegalPacketDestinationException;
import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.stream.PacketOutputStream;

public abstract class IncommingPacket extends Packet {

    protected IncommingPacket(ProtocolState state, int id) {
        super(state, id);
    }

    @Override
    public void write(PacketOutputStream stream) throws PacketException {
        throw new IllegalPacketDestinationException(this, PacketDestination.CLIENT);
    }

}
