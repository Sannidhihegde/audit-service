package com.audit.listener;

import com.audit.event.AgentInteractionEvent;
import com.audit.event.LowStockEvent;
import com.audit.store.AuditStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuditEventListener {

    private final AuditStore auditStore;

    public AuditEventListener(AuditStore auditStore) {
        this.auditStore = auditStore;
    }

    @KafkaListener(topics = "agent-interactions", groupId = "audit-service",
            properties = "spring.json.value.default.type=com.audit.event.AgentInteractionEvent")
    public void handleInteraction(AgentInteractionEvent event) {
        log.info("Received interaction: question='{}'", event.question());
        auditStore.saveInteraction(event);
    }

    @KafkaListener(topics = "low-stock-alerts", groupId = "audit-service",
            properties = "spring.json.value.default.type=com.audit.event.LowStockEvent")
    public void handleLowStock(LowStockEvent event) {
        log.warn("Low stock alert recorded: {} (qty={})", event.name(), event.quantity());
        auditStore.saveLowStock(event);
    }
}