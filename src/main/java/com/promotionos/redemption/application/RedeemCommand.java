package com.promotionos.redemption.application;

import lombok.Value;
import java.util.List;
import java.util.UUID;

@Value
public class RedeemCommand {
    String tenantId;
    String idempotencyKey;
    UUID customerId;
    UUID campaignId;
    double discountAmount;
    double cartTotal;
    String storeId;
    String division;
    List<String> cartUPCs;
}
