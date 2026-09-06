package com.audit.event;

public record LowStockEvent(String itemId, String name, int quantity, int threshold) {}