package org.pistonmc.world;

import org.pistonmc.inventory.material.MaterialData;

public class Block {

    private Location location;
    private MaterialData data;

    public Block(MaterialData data) {
        this(null, data);
    }

    protected Block(Location location, MaterialData data) {
        this.location = location;
        this.data = data;
    }

    public Location getLocation() {
        return location;
    }

    public MaterialData getData() {
        return data;
    }

}
