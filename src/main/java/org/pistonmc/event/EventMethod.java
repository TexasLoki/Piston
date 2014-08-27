package org.pistonmc.event;

import org.pistonmc.logging.Logger;

import java.lang.reflect.Method;

public class EventMethod<E extends Event> {

    private Listener listener;
    private EventHandler handler;
    private Method method;
    private Class<? extends E> event;

    public EventMethod(Listener listener, EventHandler handler, Method method, Class<? extends E> event) {
        this.listener = listener;
        this.handler = handler;
        this.method = method;
        this.event = event;
    }

    public Listener getListener() {
        return listener;
    }

    public EventHandler getHandler() {
        return handler;
    }

    public Method getMethod() {
        return method;
    }

    public Class<? extends E> getEvent() {
        return event;
    }

    public void handle(E event, Logger logger) {
        if(handler.ignoreCancelled() && event instanceof Cancellable && ((Cancellable) event).isCancelled()) {
            return;
        }

        if((handler.classMatches() && this.event.equals(event.getClass())) || (!handler.classMatches() && this.event.isAssignableFrom(event.getClass()))) {
            try {
                method.invoke(listener, event);
            } catch(Exception ex) {
                logger.log("Could not invoke " + method.getName() + "(" + this.event.getSimpleName() + ") from " + listener.getClass().getSimpleName(), ex);
            }
        }
    }

}
