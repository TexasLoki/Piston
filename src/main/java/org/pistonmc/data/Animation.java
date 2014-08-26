package org.pistonmc.data;

public enum Animation {

    SWING_ARM(0),
    DAMAGE(1),
    LEAVE_BED(2),
    EAT_FOOD(3),
    CRITICAL(4),
    MAGIC_CRITICAL(5),
    UNKNOWN(102),
    CROUCH(103),
    UNCROUNCH(104);

    private int id;

    Animation(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Animation valueOf(int id) {
        for(Animation animation : values()) {
            if(animation.getId() == id) {
                return animation;
            }
        }

        return UNKNOWN;
    }

}
