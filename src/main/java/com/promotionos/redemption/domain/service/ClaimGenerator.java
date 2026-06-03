package com.promotionos.redemption.domain.service;

import com.promotionos.redemption.domain.model.Claim;
import com.promotionos.redemption.domain.model.Redemption;
import java.util.UUID;

public interface ClaimGenerator {
    Claim generate(Redemption redemption, double vendorShare, String vendorId);
    void scheduleT24(UUID redemptionId);
}
