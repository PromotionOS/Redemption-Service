package com.promotionos.redemption.application;

import com.promotionos.redemption.domain.event.DomainEvent;
import java.util.List;

public interface DomainEventPublisher {
    void publishAll(List<DomainEvent> events);
}
