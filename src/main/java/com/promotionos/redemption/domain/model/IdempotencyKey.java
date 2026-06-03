package com.promotionos.redemption.domain.model;

import lombok.Value;

@Value
public class IdempotencyKey {
    // Format: pos-{storeId}-txn-{transactionId}
    String key;
}
