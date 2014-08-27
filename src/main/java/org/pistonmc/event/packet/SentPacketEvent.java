package org.pistonmc.event.packet;

import org.pistonmc.protocol.packet.OutgoingPacket;

public class SentPacketEvent extends PacketEvent {

    public SentPacketEvent(OutgoingPacket packet) {
        super(packet);
    }

    @Override
    public OutgoingPacket getPacket() {
        return (OutgoingPacket) super.getPacket();
    }

}
