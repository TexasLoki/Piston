package org.pistonmc.event.connection;

import org.json.JSONObject;
import org.pistonmc.ChatColor;
import org.pistonmc.event.Cancellable;
import org.pistonmc.event.Event;
import org.pistonmc.exception.IllegalMotdException;
import org.pistonmc.util.ChatFormatter;

import java.util.List;

public class ServerListPingEvent extends Event implements Cancellable {

    private String protocolName;
    private int realProtocolVersion;
    private int protocolVersion;

    private int onlinePlayers;
    private int maxPlayers;

    private String motd;

    private boolean cancelled;

    public ServerListPingEvent(String protocolName, int protocolVersion, int onlinePlayers, int maxPlayers, String motd) {
        this.protocolName = protocolName;
        this.realProtocolVersion = protocolVersion;
        this.protocolVersion = protocolVersion;
        this.onlinePlayers = onlinePlayers;
        this.maxPlayers = maxPlayers;
        this.motd = motd;
    }

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public boolean isAccessible() {
        return realProtocolVersion == protocolVersion || protocolVersion > 0;
    }

    public void setAccessible(boolean accessible) {
        setProtocolVersion(accessible ? realProtocolVersion : -1);
    }

    public int getOnlinePlayers() {
        return onlinePlayers;
    }

    public void setOnlinePlayers(int onlinePlayers) {
        this.onlinePlayers = onlinePlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public String getMotd() {
        return motd;
    }

    public void setMotd(String motd) {
        setMotd(false, '&', motd);
    }

    public void setMotd(boolean replace, char ch, String motd) {
        this.motd = replace ? ChatColor.translate(ch, motd) : motd;
    }

    public void setMotd(String... lines) {
        setMotd(false, '&', lines);
    }

    public void setMotd(boolean replace, char ch, String... lines) {
        if (lines.length > 2) {
            throw new IllegalMotdException("Multiline MOTDs may only contain up to 2 lines");
        }

        if (lines.length <= 0) {
            setMotd(replace, ch, "");
        } else if (lines.length == 1) {
            setMotd(replace, ch, lines[0]);
        } else {
            setMotd(replace, ch, lines[0] + "\n" + ChatColor.RESET + lines[1]);
        }
    }

    public void setMotd(List<String> lines) {
        String[] strings = new String[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            String string = lines.get(i);
            strings[i] = string;
        }

        setMotd(false, '&', strings);
    }

    public void setMotd(boolean replace, char ch, List<String> lines) {
        String[] strings = new String[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            String string = lines.get(i);
            strings[i] = string;
        }

        setMotd(replace, ch, strings);
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public JSONObject serialize() {
        JSONObject json = new JSONObject();

        JSONObject version = new JSONObject();
        version.put("name", protocolName);
        version.put("protocol", protocolVersion);

        JSONObject players = new JSONObject();
        players.put("max", maxPlayers);
        players.put("online", onlinePlayers);

        JSONObject description = ChatFormatter.serialize(motd);

        json.put("version", version);
        json.put("players", players);
        json.put("description", description);
        return json;
    }

}
