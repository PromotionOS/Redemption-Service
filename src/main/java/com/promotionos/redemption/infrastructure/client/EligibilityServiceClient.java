package com.promotionos.redemption.infrastructure.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Component
public class EligibilityServiceClient {

    private final RestTemplate restTemplate;

    @Value("${promotionos.services.eligibility-url}")
    private String eligibilityUrl;

    public EligibilityServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public EligibilityResult check(UUID campaignId, String tenantId,
                                   UUID customerId, double cartTotal,
                                   List<String> cartUPCs) {
        EligibilityCheckRequest request = new EligibilityCheckRequest(
                tenantId, customerId, campaignId, cartTotal, cartUPCs
        );
        return restTemplate.postForObject(
                eligibilityUrl + "/eligibility/check",
                request,
                EligibilityResult.class
        );
    }

    public record EligibilityCheckRequest(
            String tenantId,
            UUID customerId,
            UUID campaignId,
            double cartTotal,
            List<String> cartUPCs
    ) {}

    public record EligibilityResult(boolean eligible, String reason) {}
}
