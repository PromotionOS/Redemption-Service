package com.promotionos.redemption.infrastructure.service;

import com.promotionos.redemption.domain.model.IdempotencyKey;
import com.promotionos.redemption.domain.model.TenantId;
import com.promotionos.redemption.domain.service.IdempotencyGuard;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class IdempotencyGuardImpl implements IdempotencyGuard {

    @Override
    public boolean isDuplicate(IdempotencyKey key, TenantId tenantId) {
        // TODO Team 3 Sprint 1 — check Redis for existing key
        throw new UnsupportedOperationException("IdempotencyGuard not implemented");
    }

    @Override
    public void register(IdempotencyKey key, TenantId tenantId, UUID redemptionId) {
        // TODO Team 3 Sprint 1
        throw new UnsupportedOperationException("IdempotencyGuard not implemented");
    }

    @Override
    public UUID getOriginalRedemptionId(IdempotencyKey key, TenantId tenantId) {
        // TODO Team 3 Sprint 1
        throw new UnsupportedOperationException("IdempotencyGuard not implemented");
    }
}
