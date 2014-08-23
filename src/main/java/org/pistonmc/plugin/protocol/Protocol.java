package org.pistonmc.plugin.protocol;

import org.pistonmc.plugin.JavaPlugin;
import org.pistonmc.protocol.Client;

public abstract class Protocol extends JavaPlugin {

    private Client client;

    protected Protocol(Client client) {
        this.client = client;
    }

    public abstract Protocol create(Client client);

}
