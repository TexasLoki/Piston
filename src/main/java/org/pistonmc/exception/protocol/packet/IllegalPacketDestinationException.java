package org.pistonmc.exception.protocol.packet;

import org.pistonmc.protocol.packet.Packet;
import org.pistonmc.protocol.packet.PacketDestination;

import static org.pistonmc.protocol.packet.PacketDestination.CLIENT;

public class IllegalPacketDestinationException extends PacketException {

    private PacketDestination destination;

    public IllegalPacketDestinationException(Packet packet, PacketDestination destination) {
        super(packet, "Cannot " + (destination == CLIENT ? "read" : "write") + " an " + (destination == CLIENT ? "outgoing" : "incomming") + " Packet");
    }

    public PacketDestination getDestination() {
        return destination;
    }

}
