package org.pistonmc.event.packet;

import org.pistonmc.event.Event;
import org.pistonmc.protocol.packet.Packet;

public class PacketEvent extends Event {

	private Packet packet;

	public PacketEvent(Packet packet) {
		this.packet = packet;
	}

	public Packet getPacket() {
		return packet;
	}

}
