package com.promotionos.redemption.api;

import java.math.BigDecimal;
import java.util.UUID;

public record RedemptionStats(
        UUID campaignId,
        String tenantId,
        long totalRedemptions,
        BigDecimal totalDiscountApplied,
        long totalClaims
) {}
