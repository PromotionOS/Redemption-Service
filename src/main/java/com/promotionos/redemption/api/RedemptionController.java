package com.promotionos.redemption.api;

import com.promotionos.redemption.application.RedeemCommand;
import com.promotionos.redemption.application.RedemptionApplicationService;
import com.promotionos.redemption.domain.model.Redemption;
import com.promotionos.redemption.domain.model.TenantId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RedemptionController {

    private final RedemptionApplicationService redemptionService;

    @PostMapping("/redeem")
    public ResponseEntity<?> redeem(@RequestBody RedeemRequest request) {
        // TODO Team 3 Sprint 1
        throw new UnsupportedOperationException("Redemption not implemented");
    }

    @GetMapping("/redemptions/{id}")
    public ResponseEntity<Redemption> findById(@PathVariable UUID id,
                                               @RequestParam String tenantId) {
        // TODO Team 3 Sprint 2
        throw new UnsupportedOperationException("Find by id not implemented");
    }

    @GetMapping("/redemptions")
    public ResponseEntity<List<Redemption>> findByCustomer(
            @RequestParam UUID customerId,
            @RequestParam String tenantId,
            @RequestParam(defaultValue = "0") int page) {
        // TODO Team 3 Sprint 3
        throw new UnsupportedOperationException("Find by customer not implemented");
    }

    @GetMapping("/redemptions/stats")
    public ResponseEntity<RedemptionStats> stats(@RequestParam UUID campaignId,
                                                 @RequestParam String tenantId) {
        // TODO Team 3 Sprint 3
        throw new UnsupportedOperationException("Stats not implemented");
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }
}
