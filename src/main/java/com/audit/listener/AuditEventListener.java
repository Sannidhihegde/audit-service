package com.audit.listener;

import com.audit.event.AgentInteractionEvent;
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

    @KafkaListener(topics = "agent-interactions", groupId = "audit-service")
    public void handle(AgentInteractionEvent event) {
        log.info("Received interaction: question='{}'", event.question());
        auditStore.save(event);
    }
}