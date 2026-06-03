package com.promotionos.redemption.infrastructure.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class CampaignServiceClient {

    private final RestTemplate restTemplate;

    @Value("${promotionos.services.campaign-url}")
    private String campaignUrl;

    public CampaignServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CampaignSummary getSummary(UUID campaignId, String tenantId) {
        return restTemplate.getForObject(
                campaignUrl + "/campaigns/" + campaignId + "/summary?tenantId=" + tenantId,
                CampaignSummary.class
        );
    }

    public record CampaignSummary(UUID id, String name, double vendorShare, String vendorId) {}
}
