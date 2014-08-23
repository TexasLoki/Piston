package org.pistonmc.plugin.protocol;

import org.pistonmc.protocol.Client;
import org.pistonmc.plugin.Plugin;

public abstract class Protocol extends Plugin {

    private Client client;

    protected Protocol(Client client) {
        this.client = client;
    }

    public abstract Protocol create(Client client);

}
