package org.pistonmc;

import org.pistonmc.entity.Entity;
import org.pistonmc.entity.LivingEntity;
import org.pistonmc.entity.Player;

import java.util.List;
import java.util.UUID;

public interface World {

    public UUID getUniqueId();

    public String getName();

    public List<Entity> getEntities();

    public List<LivingEntity> getLivingEntities();

    public List<Player> getPlayers();

}
