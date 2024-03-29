package org.pistonmc.protocol;

import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.protocol.packet.OutgoingPacket;
import org.pistonmc.protocol.packet.ProtocolState;

import javax.crypto.SecretKey;
import java.io.IOException;

public interface PlayerConnection {

    public ProtocolState getState();

    public void secure(SecretKey key);

    public void unsecure();

    public boolean isSecured();

    public SecretKey getSecretKey();

    public void sendPacket(OutgoingPacket packet) throws PacketException, IOException;

    public void close();

}
