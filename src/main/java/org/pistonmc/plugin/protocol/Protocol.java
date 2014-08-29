package org.pistonmc.plugin.protocol;

import io.netty.channel.ChannelHandlerContext;
import org.pistonmc.Piston;
import org.pistonmc.event.connection.ServerListPingEvent;
import org.pistonmc.exception.protocol.IllegalProtocolException;
import org.pistonmc.exception.protocol.packet.PacketException;
import org.pistonmc.plugin.JavaPlugin;
import org.pistonmc.protocol.PlayerConnection;
import org.pistonmc.protocol.packet.IncomingPacket;
import org.pistonmc.protocol.packet.ProtocolState;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Protocol extends JavaPlugin {

    private Map<ProtocolState, Map<Integer, Class<? extends IncomingPacket>>> packets;
    private Protocol parent;
    protected int version;
    protected PlayerConnection connection;

    protected Protocol(int version) {
        this.version = version;
        packets = new HashMap<>();
        for (ProtocolState state : ProtocolState.values()) {
            packets.put(state, new HashMap<Integer, Class<? extends IncomingPacket>>());
        }
    }

    protected Protocol(Protocol parent, ProtocolManager manager) {
        this(parent.version);
        this.parent = parent;
        this.packets = parent.packets;
        manager.load(this, parent, false);
    }

    protected Protocol(Protocol parent, PlayerConnection connection, ProtocolManager manager) {
        this(parent, manager);
        this.connection = connection;
    }

    protected void add(IncomingPacket packet) {
        if (parent != null) {
            parent.add(packet);
        } else {
            packets.get(packet.getState()).put(packet.getId(), packet.getClass());
        }
    }

    public Class<? extends IncomingPacket> find(ProtocolState state, int id) {
        return parent != null ? parent.find(state, id) : packets.get(state).get(id);
    }

    public IncomingPacket create(ProtocolState state, int id) throws IllegalProtocolException {
        Class<? extends IncomingPacket> cls = find(state, id);

        try {
            return cls.getConstructor().newInstance();
        } catch (Exception ex) {
            throw new IllegalProtocolException(version, "Could not find " + state.name() + " Packet with id #" + id);
        }
    }

    public abstract void handle(IncomingPacket packet, ChannelHandlerContext ctx) throws PacketException, IOException;

    public abstract Protocol create(PlayerConnection connection, ProtocolManager manager);

    public ServerListPingEvent response() {
        ServerListPingEvent event = new ServerListPingEvent(getDescription().getName(), version, 0, 20, "A Minecraft Server");

        List<String> motd = Piston.getConfig().getStringList("ping.motd");
        if (motd != null) {
            event.setMotd(true, '&', motd);
        }

        String protocol = Piston.getConfig().getString("ping.protocol");
        if (protocol != null && !protocol.equalsIgnoreCase("default")) {
            event.setProtocolName(protocol);
        }

        Boolean accessible = Piston.getConfig().getBoolean("ping.accessible");
        event.setAccessible(accessible == null || accessible);

        return event;
    }

}
