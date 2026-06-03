package com.promotionos.redemption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RedemptionServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedemptionServiceApplication.class, args);
    }
}
