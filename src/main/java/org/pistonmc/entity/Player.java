package org.pistonmc.entity;

import org.pistonmc.commands.CommandSender;

public interface Player extends LivingEntity, CommandSender {

    public String getDisplayName();

    public void setDisplayName(String name);

}
