package com.audit.store;

import com.audit.event.AgentInteractionEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class AuditStore {

    private final List<AgentInteractionEvent> events = new CopyOnWriteArrayList<>();

    public void save(AgentInteractionEvent event) {
        events.add(event);
    }

    public List<AgentInteractionEvent> getAll() {
        return events;
    }
}