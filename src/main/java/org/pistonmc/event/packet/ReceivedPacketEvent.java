package org.pistonmc.event.packet;

import org.pistonmc.protocol.packet.IncomingPacket;

public class ReceivedPacketEvent extends PacketEvent {

	public ReceivedPacketEvent(IncomingPacket packet) {
		super(packet);
	}

    @Override
    public IncomingPacket getPacket() {
        return (IncomingPacket) super.getPacket();
    }

}
