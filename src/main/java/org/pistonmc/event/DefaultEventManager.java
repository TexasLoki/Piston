package org.pistonmc.event;

import org.pistonmc.logging.Logger;

public class DefaultEventManager extends EventManager<Event> {

    public DefaultEventManager(Logger logger) {
        super(logger);
    }

    public DefaultEventManager() {
        super();
    }

}
