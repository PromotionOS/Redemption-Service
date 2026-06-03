package com.promotionos.redemption.infrastructure.service;

import com.promotionos.redemption.domain.model.Claim;
import com.promotionos.redemption.domain.model.Redemption;
import com.promotionos.redemption.domain.service.ClaimGenerator;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class ClaimGeneratorImpl implements ClaimGenerator {

    @Override
    public Claim generate(Redemption redemption, double vendorShare, String vendorId) {
        // TODO Team 3 Sprint 2 — deduction = discountApplied * vendorShare%
        throw new UnsupportedOperationException("ClaimGenerator not implemented");
    }

    @Override
    public void scheduleT24(UUID redemptionId) {
        // TODO Team 3 Sprint 2 — schedule claim for T+24h
        throw new UnsupportedOperationException("ClaimGenerator not implemented");
    }
}
