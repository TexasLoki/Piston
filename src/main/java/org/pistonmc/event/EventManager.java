package org.pistonmc.event;

import org.pistonmc.Piston;
import org.pistonmc.logging.Logger;
import org.pistonmc.logging.Logging;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class EventManager<E extends Event> {

    private Logger logger;
    private Map<Class<? extends E>, PrioritisedMethods<E>> events;

    public EventManager(Logger logger) {
        this.logger = logger;
        this.events = new HashMap<>();
    }

    public EventManager() {
        this(Logging.getLogger(EventManager.class, Piston.getServer().getLogger()));
    }

    public void register(Listener listener) {
        Class<?> clazz = listener.getClass();

        Method[] methods = clazz.getMethods();
        for(Method method : methods) {
            if(!method.isAnnotationPresent(EventHandler.class)) {
                continue;
            }

            EventHandler handler = method.getAnnotation(EventHandler.class);
            if(method.getParameterTypes().length != 1) {
                continue;
            }

            Class<?> parameter = method.getParameterTypes()[0];
            Class<? extends E> event;
            try {
                event = (Class<? extends E>) parameter;
            } catch(Exception ex) {
                continue;
            }

            if(events == null) {
                continue;
            }

            PrioritisedMethods<E> prioritised = events.containsKey(event) ? events.get(event) : null;
            if(prioritised == null) {
                prioritised = new PrioritisedMethods<>();
                events.put(event, prioritised);
            }

            prioritised.add(listener, handler, method, event);
        }
    }

    public void unregister(Listener listener) {
        for(PrioritisedMethods<E> methods : events.values()) {
            methods.remove(listener);
        }
    }

    public void unregister(Class<? extends E> event) {
        unregister(event, false);
    }

    public void unregister(Class<? extends E> event, boolean matches) {
        for(PrioritisedMethods<E> methods : events.values()) {
            methods.remove(event, !matches);
        }
    }

    public <T extends E> T call(T event) {
        try {
            List<PrioritisedMethods<E>> methods = new ArrayList<>();
            for(Entry<Class<? extends E>, PrioritisedMethods<E>> entry : events.entrySet()) {
                if(entry.getKey().isAssignableFrom(event.getClass())) {
                    methods.add(entry.getValue());
                }
            }

            Map<EventPriority, List<EventMethod<E>>> priorityMap = new HashMap<>();
            for(EventPriority priority : EventPriority.values()) {
                List<EventMethod<E>> list = new ArrayList<>();
                for(PrioritisedMethods<E> method : methods) {
                    list.addAll(method.getMethods(priority));
                }
                priorityMap.put(priority, list);
            }

            for(EventPriority priority : EventPriority.values()) {
                for(EventMethod<E> method : priorityMap.get(priority)) {
                    method.handle(event, logger);
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return event;
    }

}
