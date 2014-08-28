package org.pistonmc.data;

public enum Direction {

    NORTH(2),
    EAST(3),
    SOUTH(0),
    WEST(1);

    private int id;

    Direction(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Direction valueOf(int id) {
        for (Direction direction : values()) {
            if (direction.getId() == id) {
                return direction;
            }
        }

        return null;
    }

}
