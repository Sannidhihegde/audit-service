package com.audit.store;

import com.audit.event.AgentInteractionEvent;
import com.audit.event.LowStockEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class AuditStore {

    private final List<AgentInteractionEvent> interactions = new CopyOnWriteArrayList<>();
    private final List<LowStockEvent> lowStockAlerts = new CopyOnWriteArrayList<>();

    public void saveInteraction(AgentInteractionEvent event) {
        interactions.add(event);
    }

    public void saveLowStock(LowStockEvent event) {
        lowStockAlerts.add(event);
    }

    public List<AgentInteractionEvent> getInteractions() {
        return interactions;
    }

    public List<LowStockEvent> getLowStockAlerts() {
        return lowStockAlerts;
    }
}