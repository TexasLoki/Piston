package org.pistonmc.event;

import org.pistonmc.util.ClassUtils;

public class Event {

    @Override
    public String toString() {
        return ClassUtils.build(this, true);
    }

}
