package org.pistonmc.world;

import org.pistonmc.entity.Entity;
import org.pistonmc.entity.LivingEntity;
import org.pistonmc.entity.Player;

import java.io.File;
import java.util.List;
import java.util.UUID;

public interface World {

    public File getFolder();

    public String getName();

    public List<Entity> getEntities();
    
    public <T extends Entity> List<T> getEntities(Class<T> cls);

    public Chunk getChunk(int x, int z);

    public Block getBlock(int x, int y, int z);

}
