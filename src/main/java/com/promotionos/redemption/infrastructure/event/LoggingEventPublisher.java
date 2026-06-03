package com.promotionos.redemption.infrastructure.event;

import com.promotionos.redemption.application.DomainEventPublisher;
import com.promotionos.redemption.domain.event.DomainEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.List;

// Fallback publisher used when Redis is unavailable (local dev without Redis)
@Slf4j
@Component
@ConditionalOnMissingBean(EventPublisher.class)
public class LoggingEventPublisher implements DomainEventPublisher {

    @Override
    public void publishAll(List<DomainEvent> events) {
        events.forEach(e -> log.warn("[NO-OP] Event not published (no Redis): {} {}", e.getEventType(), e.getEventId()));
    }
}
