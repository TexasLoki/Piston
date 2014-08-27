package org.pistonmc.plugin.protocol;

import org.pistonmc.exception.protocol.IllegalProtocolException;
import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.plugin.JavaPlugin;
import org.pistonmc.protocol.PlayerConnection;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.packet.Packet;
import org.pistonmc.protocol.packet.ProtocolState;

import java.util.HashMap;
import java.util.Map;

public abstract class Protocol extends JavaPlugin {

    private static Map<ProtocolState, Map<Integer, Class<? extends IncomingPacket>>> packets;

    protected int version;
    protected PlayerConnection connection;

    protected Protocol(int version) {
        this(version, null);
    }

    protected Protocol(int version, PlayerConnection connection) {
        this.version = version;
        this.connection = connection;
        packets = new HashMap<>();
        for(ProtocolState state : ProtocolState.values()) {
            packets.put(state, new HashMap<Integer, Class<? extends IncomingPacket>>());
        }
    }

    protected void add(IncomingPacket packet) {
        packets.get(packet.getState()).put(packet.getId(), packet.getClass());
    }

    public Class<? extends IncomingPacket> find(ProtocolState state, int id) {
        return packets.get(state).get(id);
    }

    public IncomingPacket create(ProtocolState state, int id) throws IllegalProtocolException {
        Class<? extends IncomingPacket> cls = find(state, id);

        try {
            return cls.getConstructor().newInstance();
        } catch(Exception ex) {
            throw new IllegalProtocolException(version, "Could not find " + state.name() + " Packet with id #" + id);
        }
    }

    public abstract void handle(Packet packet) throws PacketException;

    public abstract Protocol create(PlayerConnection connection);

}
