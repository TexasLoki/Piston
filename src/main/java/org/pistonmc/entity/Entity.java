package org.pistonmc.entity;

import org.pistonmc.Location;
import org.pistonmc.World;

import java.util.UUID;

public interface Entity {

    public String getName();

    public int getEntityId();

    public UUID getUniqueId();

    public Location getLocation();

    public World getWorld();

    public boolean isDead();

}
