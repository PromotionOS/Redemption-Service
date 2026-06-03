package com.promotionos.redemption.api;

import java.util.List;
import java.util.UUID;

public record RedeemRequest(
        String tenantId,
        String idempotencyKey,
        UUID customerId,
        UUID campaignId,
        double discountAmount,
        double cartTotal,
        String storeId,
        String division,
        List<String> cartUPCs
) {}
