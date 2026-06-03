package com.promotionos.redemption.application;

import com.promotionos.redemption.domain.model.Redemption;
import com.promotionos.redemption.domain.model.TenantId;
import com.promotionos.redemption.domain.repository.RedemptionRepository;
import com.promotionos.redemption.domain.service.ClaimGenerator;
import com.promotionos.redemption.domain.service.IdempotencyGuard;
import com.promotionos.redemption.infrastructure.client.CampaignServiceClient;
import com.promotionos.redemption.infrastructure.client.EligibilityServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RedemptionApplicationService {

    private final RedemptionRepository redemptionRepository;
    private final IdempotencyGuard idempotencyGuard;
    private final ClaimGenerator claimGenerator;
    private final EligibilityServiceClient eligibilityClient;
    private final CampaignServiceClient campaignClient;
    private final DomainEventPublisher eventPublisher;

    public Redemption redeem(RedeemCommand cmd) {
        // TODO Team 3 Sprint 1 — implement in this order:
        // 1. idempotencyGuard.isDuplicate() — if true return 409
        // 2. eligibilityClient.check() — if ineligible return 400
        // 3. Redemption.confirm()
        // 4. idempotencyGuard.register()
        // 5. redemptionRepository.save()
        // 6. eventPublisher.publishAll(redemption.pullDomainEvents())
        throw new UnsupportedOperationException("Redemption not implemented");
    }

    public Optional<Redemption> findById(UUID id, TenantId tenantId) {
        // TODO Team 3 Sprint 2
        throw new UnsupportedOperationException("Find by id not implemented");
    }

    public List<Redemption> findByCustomer(UUID customerId, TenantId tenantId, int page) {
        // TODO Team 3 Sprint 3
        throw new UnsupportedOperationException("Find by customer not implemented");
    }

    public void processPendingClaims() {
        // TODO Team 3 Sprint 2 — scheduled job, runs every minute
        // finds redemptions where scheduledAt <= now and no claim exists
        // generates claim, saves, publishes ClaimSubmitted
        throw new UnsupportedOperationException("Claim processing not implemented");
    }
}
