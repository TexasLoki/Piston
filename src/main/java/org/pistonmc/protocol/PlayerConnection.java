package org.pistonmc.protocol;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.Packet;

import java.io.IOException;

public interface PlayerConnection {

    public void sendPacket(OutgoingPacket packet) throws PacketException, IOException;

}
