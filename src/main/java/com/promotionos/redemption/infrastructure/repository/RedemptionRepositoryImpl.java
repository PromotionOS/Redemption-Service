package com.promotionos.redemption.infrastructure.repository;

import com.promotionos.redemption.domain.model.Redemption;
import com.promotionos.redemption.domain.model.TenantId;
import com.promotionos.redemption.domain.repository.RedemptionRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RedemptionRepositoryImpl implements RedemptionRepository {

    @Override
    public Redemption save(Redemption redemption) {
        // TODO Team 3 Sprint 1 — persist to PostgreSQL via JPA
        throw new UnsupportedOperationException("save not implemented");
    }

    @Override
    public Optional<Redemption> findById(UUID id, TenantId tenantId) {
        // TODO Team 3 Sprint 2
        throw new UnsupportedOperationException("findById not implemented");
    }

    @Override
    public List<Redemption> findByCustomer(UUID customerId, TenantId tenantId, int page, int size) {
        // TODO Team 3 Sprint 3
        throw new UnsupportedOperationException("findByCustomer not implemented");
    }

    @Override
    public List<Redemption> findByCampaign(UUID campaignId, TenantId tenantId, int page, int size) {
        // TODO Team 3 Sprint 3
        throw new UnsupportedOperationException("findByCampaign not implemented");
    }

    @Override
    public List<Redemption> findPendingClaims(Instant before) {
        // TODO Team 3 Sprint 2
        throw new UnsupportedOperationException("findPendingClaims not implemented");
    }
}
