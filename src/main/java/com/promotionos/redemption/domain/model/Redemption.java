package com.promotionos.redemption.domain.model;

import com.promotionos.redemption.domain.event.ClaimSubmitted;
import com.promotionos.redemption.domain.event.DomainEvent;
import com.promotionos.redemption.domain.event.OfferRedeemed;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Redemption {
    private UUID id;
    private TenantId tenantId;
    private IdempotencyKey idempotencyKey;
    private CustomerId customerId;
    private UUID campaignId;
    private Money discountApplied;
    private Money cartTotal;
    private String storeId;
    private String division;
    private Instant redeemedAt;
    private RedemptionStatus status;
    private Claim claim;
    private List<DomainEvent> domainEvents = new ArrayList<>();

    // Immutable after confirm() — no public setters on core fields
    public static Redemption confirm(TenantId tenantId, IdempotencyKey key,
                                     CustomerId customerId, UUID campaignId,
                                     Money discount, Money cartTotal,
                                     String storeId, String division) {
        Redemption r = new Redemption();
        r.id = UUID.randomUUID();
        r.tenantId = tenantId;
        r.idempotencyKey = key;
        r.customerId = customerId;
        r.campaignId = campaignId;
        r.discountApplied = discount;
        r.cartTotal = cartTotal;
        r.storeId = storeId;
        r.division = division;
        r.redeemedAt = Instant.now();
        r.status = RedemptionStatus.CONFIRMED;
        r.domainEvents.add(new OfferRedeemed(r));
        return r;
    }

    public void attachClaim(Claim claim) {
        this.claim = claim;
        this.status = RedemptionStatus.CLAIMED;
        this.domainEvents.add(new ClaimSubmitted(claim));
    }

    public List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> events = new ArrayList<>(this.domainEvents);
        this.domainEvents.clear();
        return events;
    }
}
