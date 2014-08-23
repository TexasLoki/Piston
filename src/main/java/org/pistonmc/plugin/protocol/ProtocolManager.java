package org.pistonmc.plugin.protocol;

import com.google.common.collect.Lists;
import org.pistonmc.exception.protocol.ProtocolException;
import org.pistonmc.exception.protocol.ProtocolNotFoundException;
import org.pistonmc.logging.Logger;
import org.pistonmc.plugin.PluginManager;
import org.pistonmc.protocol.Client;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProtocolManager extends PluginManager<Protocol> {

    private Map<String, Protocol> protocols;

    public ProtocolManager(Logger logger, File folder) {
        super(logger, folder, "protocol.json");
        protocols = new HashMap<>();
    }

    public List<Protocol> getProtocols() {
        return Lists.newArrayList(protocols.values());
    }

    public Protocol find(String version) throws ProtocolException {
        return find(version, null);
    }

    public Protocol find(String version, Client client) throws ProtocolException {
        Protocol result = protocols.get(version);
        if(result == null) {
            throw new ProtocolNotFoundException(version);
        }

        return result.create(client);
    }

}
