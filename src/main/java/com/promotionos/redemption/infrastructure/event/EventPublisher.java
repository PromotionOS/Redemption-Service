package com.promotionos.redemption.infrastructure.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.promotionos.redemption.application.DomainEventPublisher;
import com.promotionos.redemption.domain.event.ClaimSubmitted;
import com.promotionos.redemption.domain.event.DomainEvent;
import com.promotionos.redemption.domain.event.OfferRedeemed;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EventPublisher implements DomainEventPublisher {

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    @Value("${promotionos.redis.channels.offer-redeemed}")
    private String offerRedeemedChannel;

    @Value("${promotionos.redis.channels.claim-submitted}")
    private String claimSubmittedChannel;

    public void publishAll(List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    private void publish(DomainEvent event) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            String channel = resolveChannel(event);
            redisTemplate.convertAndSend(channel, payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize event: " + event.getEventType(), e);
        }
    }

    private String resolveChannel(DomainEvent event) {
        if (event instanceof OfferRedeemed) return offerRedeemedChannel;
        if (event instanceof ClaimSubmitted) return claimSubmittedChannel;
        throw new IllegalArgumentException("Unknown event type: " + event.getEventType());
    }
}
