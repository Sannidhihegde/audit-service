package com.audit.controller;

import com.audit.event.AgentInteractionEvent;
import com.audit.event.LowStockEvent;
import com.audit.store.AuditStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuditController {

    private final AuditStore auditStore;

    public AuditController(AuditStore auditStore) {
        this.auditStore = auditStore;
    }

    @GetMapping("/audit/low-stock")
    public List<LowStockEvent> getLowStockAlerts() {
        return auditStore.getLowStockAlerts();
    }
}