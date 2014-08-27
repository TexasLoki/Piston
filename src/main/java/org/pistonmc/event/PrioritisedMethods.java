package org.pistonmc.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrioritisedMethods<E extends Event> {

    private Map<EventPriority, List<EventMethod<E>>> methods;

    public PrioritisedMethods() {
        methods = new HashMap<>();
        for(EventPriority priority : EventPriority.values()) {
            methods.put(priority, new ArrayList<EventMethod<E>>());
        }
    }

    public List<EventMethod<E>> getMethods(EventPriority priority) {
        return methods.get(priority);
    }

    public List<EventMethod<E>> getOrderedMethods() {
        List<EventMethod<E>> methods = new ArrayList<>();
        for(EventPriority priority : EventPriority.values()) {
            methods.addAll(getMethods(priority));
        }

        return methods;
    }

    public void add(Listener listener, EventHandler handler, Method method, Class<? extends E> event) {
        methods.get(handler.value()).add(new EventMethod<>(listener, handler, method, event));
    }

    public void remove(Listener listener) {
        for(List<EventMethod<E>> methods : this.methods.values()) {
            List<EventMethod<E>> remove = new ArrayList<>();
            for(EventMethod<E> method : methods) {
                if(method.getListener().equals(listener)) {
                    remove.add(method);
                }
            }

            methods.removeAll(remove);
        }
    }

    public void remove(Class<? extends E> event, boolean assignable) {
        for(List<EventMethod<E>> methods : this.methods.values()) {
            List<EventMethod<E>> remove = new ArrayList<>();
            for(EventMethod<E> method : methods) {
                if((assignable && event.isAssignableFrom(method.getEvent())) || (!assignable && event.equals(method.getEvent()))) {
                    remove.add(method);
                }
            }

            methods.removeAll(remove);
        }
    }

}
