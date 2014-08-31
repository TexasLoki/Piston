package org.pistonmc.world;

import java.io.File;
import java.util.List;

public interface WorldManager {

    public File getContainer();

    public List<World> getWorlds();

    public World create(WorldBuilder builder);

}
