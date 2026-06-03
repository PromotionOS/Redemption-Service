package com.promotionos.redemption.domain.service;

import com.promotionos.redemption.domain.model.IdempotencyKey;
import com.promotionos.redemption.domain.model.TenantId;
import java.util.UUID;

public interface IdempotencyGuard {
    boolean isDuplicate(IdempotencyKey key, TenantId tenantId);
    void register(IdempotencyKey key, TenantId tenantId, UUID redemptionId);
    UUID getOriginalRedemptionId(IdempotencyKey key, TenantId tenantId);
}
