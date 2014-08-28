package org.pistonmc.entity;

import org.pistonmc.commands.CommandSender;

import java.net.InetSocketAddress;

public interface Player extends LivingEntity, CommandSender {

    public String getDisplayName();

    public void setDisplayName(String name);

    public String getPlayerListName();

    public void setPlayerListName(String name);

    public InetSocketAddress getAddress();

}
