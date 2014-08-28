package org.pistonmc.event;

import org.pistonmc.Piston;
import org.pistonmc.util.ClassUtils;

public class Event {

    @Override
    public String toString() {
        return ClassUtils.build(this, true);
    }

    public void call() {
        Piston.getEventManager().call(this);
    }

}
