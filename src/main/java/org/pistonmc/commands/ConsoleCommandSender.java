package org.pistonmc.commands;

import org.pistonmc.Piston;

public class ConsoleCommandSender implements CommandSender {

    @Override
    public String getName() {
        return "Console";
    }

    @Override
    public void sendMessage(String message) {
        Piston.getLogger().info(message);
    }

}
