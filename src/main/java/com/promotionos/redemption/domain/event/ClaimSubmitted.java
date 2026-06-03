package com.promotionos.redemption.domain.event;

import com.promotionos.redemption.domain.model.Claim;
import lombok.Getter;
import java.time.Instant;
import java.util.UUID;

@Getter
public class ClaimSubmitted implements DomainEvent {
    private final UUID eventId = UUID.randomUUID();
    private final Instant occurredAt = Instant.now();
    private final Claim claim;

    public ClaimSubmitted(Claim claim) {
        this.claim = claim;
    }

    @Override
    public String getEventType() {
        return "ClaimSubmitted";
    }
}
