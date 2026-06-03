CREATE TABLE IF NOT EXISTS redemptions (
    id              UUID PRIMARY KEY,
    tenant_id       VARCHAR(255) NOT NULL,
    idempotency_key VARCHAR(512) NOT NULL,
    customer_id     UUID         NOT NULL,
    campaign_id     UUID         NOT NULL,
    discount_amount NUMERIC(19, 4) NOT NULL,
    discount_currency VARCHAR(3) NOT NULL DEFAULT 'USD',
    cart_total      NUMERIC(19, 4) NOT NULL,
    cart_currency   VARCHAR(3) NOT NULL DEFAULT 'USD',
    store_id        VARCHAR(255),
    division        VARCHAR(255),
    redeemed_at     TIMESTAMP WITH TIME ZONE NOT NULL,
    status          VARCHAR(50) NOT NULL,
    created_at      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE UNIQUE INDEX IF NOT EXISTS uidx_redemptions_idempotency
    ON redemptions (tenant_id, idempotency_key);

CREATE INDEX IF NOT EXISTS idx_redemptions_customer
    ON redemptions (tenant_id, customer_id);

CREATE INDEX IF NOT EXISTS idx_redemptions_campaign
    ON redemptions (tenant_id, campaign_id);

CREATE TABLE IF NOT EXISTS claims (
    id              UUID PRIMARY KEY,
    redemption_id   UUID NOT NULL REFERENCES redemptions(id),
    tenant_id       VARCHAR(255) NOT NULL,
    vendor_id       VARCHAR(255) NOT NULL,
    amount          NUMERIC(19, 4) NOT NULL,
    amount_currency VARCHAR(3) NOT NULL DEFAULT 'USD',
    deduction       NUMERIC(19, 4) NOT NULL,
    deduction_currency VARCHAR(3) NOT NULL DEFAULT 'USD',
    status          VARCHAR(50) NOT NULL,
    scheduled_at    TIMESTAMP WITH TIME ZONE,
    submitted_at    TIMESTAMP WITH TIME ZONE,
    created_at      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at      TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_claims_redemption
    ON claims (redemption_id);

CREATE INDEX IF NOT EXISTS idx_claims_pending
    ON claims (status, scheduled_at)
    WHERE status = 'PENDING';
