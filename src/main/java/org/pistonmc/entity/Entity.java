package org.pistonmc.entity;

import org.pistonmc.world.Location;
import org.pistonmc.world.World;

import java.util.UUID;

public interface Entity {

    public String getName();

    public int getEntityId();

    public UUID getUniqueId();

    public Location getLocation();

    public World getWorld();

    public boolean isValid();

}
