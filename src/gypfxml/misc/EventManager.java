package gypfxml.misc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EventManager {
    private final Map<Event, Set<EventHandler>> handlers;
    
    public EventManager() {
        handlers = new HashMap<>();
    }
    
    public void on(Event event, EventHandler handler) {
        Set<EventHandler> currentEventHandlers = handlers.get(event);
        if (currentEventHandlers == null) {
            currentEventHandlers = new HashSet<>();
            handlers.put(event, currentEventHandlers);
        }
        currentEventHandlers.add(handler);
    }
    
    public void emit(Event event, Object... data) {
        Set<EventHandler> currentEventHandlers = handlers.get(event);
        if (currentEventHandlers != null){
            for (EventHandler handler : currentEventHandlers) {
                
                handler.handle(data);
            }
        }
    }
}
