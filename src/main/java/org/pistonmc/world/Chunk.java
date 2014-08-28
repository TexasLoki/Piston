package org.pistonmc.world;

public interface Chunk {

    public int getX();

    public int getZ();

    public World getWorld();

    public Block getBlock(int x, int y, int z);

}
