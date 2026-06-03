package com.promotionos.redemption.domain.event;

import com.promotionos.redemption.domain.model.Redemption;
import lombok.Getter;
import java.time.Instant;
import java.util.UUID;

@Getter
public class OfferRedeemed implements DomainEvent {
    private final UUID eventId = UUID.randomUUID();
    private final Instant occurredAt = Instant.now();
    private final Redemption redemption;

    public OfferRedeemed(Redemption redemption) {
        this.redemption = redemption;
    }

    @Override
    public String getEventType() {
        return "OfferRedeemed";
    }
}
