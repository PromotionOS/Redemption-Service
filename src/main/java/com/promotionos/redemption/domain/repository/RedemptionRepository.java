package com.promotionos.redemption.domain.repository;

import com.promotionos.redemption.domain.model.Redemption;
import com.promotionos.redemption.domain.model.TenantId;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RedemptionRepository {
    Redemption save(Redemption redemption);
    Optional<Redemption> findById(UUID id, TenantId tenantId);
    List<Redemption> findByCustomer(UUID customerId, TenantId tenantId, int page, int size);
    List<Redemption> findByCampaign(UUID campaignId, TenantId tenantId, int page, int size);
    List<Redemption> findPendingClaims(Instant before);
}
