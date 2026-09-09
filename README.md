# audit-service

Consumes events from Kafka and stores them for inspection. Demonstrates
asynchronous, decoupled collaboration between services, as opposed to the
synchronous REST calls used elsewhere in this system.

## Responsibilities
- Consumes `agent-interactions` topic → logs and stores every agent Q&A
- Consumes `low-stock-alerts` topic → logs and stores low-stock alerts
- Exposes `GET /audit/interactions` and `GET /audit/low-stock`
- Runs in its own Kafka consumer group (`audit-service`) — independent of
  `notification-service`, which consumes the same `low-stock-alerts` topic
  in a separate group (both receive every event — Kafka fan-out, not
  competing consumers)
- Fetches most of its config from `config-server` at startup

## Run
    mvn spring-boot:run
Runs on port 8084 (from `config-repo/audit-service.yml`). Requires
`config-server`, `eureka-server`, and `kafka-local` running first.

## Tech
Spring Boot 4.1, Spring Kafka, Spring Cloud 2025.1.2 (Eureka client,
Config client), in-memory store.

## Notes
Kafka messages carry no type headers (`spring.json.add.type.headers: false`
on the producer side) — each service deserializes into its own local copy
of the event class, keyed per-topic via per-listener
`spring.json.value.default.type` overrides. Avoids coupling producer and
consumer to identical Java class names/packages.

## Related services
Consumes events from: [agent-service](https://github.com/Sannidhihegde/agent-service), [inventory-service](https://github.com/Sannidhihegde/inventory-service)
