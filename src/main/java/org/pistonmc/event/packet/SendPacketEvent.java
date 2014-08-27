package org.pistonmc.event.packet;

import org.pistonmc.event.Cancellable;
import org.pistonmc.protocol.packet.OutgoingPacket;

public class SendPacketEvent extends PacketEvent implements Cancellable {

	private boolean cancelled;

	public SendPacketEvent(OutgoingPacket packet) {
		super(packet);
	}

    @Override
    public OutgoingPacket getPacket() {
        return (OutgoingPacket) super.getPacket();
    }

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

}
