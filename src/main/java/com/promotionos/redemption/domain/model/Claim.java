package com.promotionos.redemption.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Claim {
    private UUID id;
    private UUID redemptionId;
    private TenantId tenantId;
    private String vendorId;
    private Money amount;
    private Money deduction;
    private ClaimStatus status;
    private Instant scheduledAt;
    private Instant submittedAt;
}
