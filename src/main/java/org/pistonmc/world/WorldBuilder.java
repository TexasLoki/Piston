package org.pistonmc.world;

import org.pistonmc.Piston;

import java.io.File;

public class WorldBuilder {

    private String name;
    private File folder;
    private Dimension dimension;

    public WorldBuilder(String name) {
        this.name = name;
        this.folder = new File(name);
    }

    public String getName() {
        return name;
    }

    public File getFolder() {
        return folder;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public WorldBuilder name(String name) {
        this.name = name;
        return this;
    }

    public WorldBuilder folder(File folder) {
        this.folder = folder;
        return this;
    }

    public WorldBuilder dimension(Dimension dimension) {
        this.dimension = dimension;
        return this;
    }

    public World create() {
        return create(Piston.getWorldManager());
    }

    public World create(WorldManager manager) {
        return manager.create(this);
    }

}
