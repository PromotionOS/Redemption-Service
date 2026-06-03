package com.promotionos.redemption.domain.model;

import lombok.Value;
import java.math.BigDecimal;

@Value
public class Money {
    BigDecimal amount;
    String currency;

    public static Money of(double amount) {
        return new Money(BigDecimal.valueOf(amount), "USD");
    }

    public Money multiply(double factor) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(factor)), this.currency);
    }
}
