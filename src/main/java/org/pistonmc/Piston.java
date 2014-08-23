package org.pistonmc;

public class Piston {

    private static Server server;

    public static Server getServer() {
        return server;
    }

    public static void shutdown() {
        server.shutdown();
    }

}
