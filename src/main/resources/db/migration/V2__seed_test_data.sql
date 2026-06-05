-- Seed data for local development and integration tests only
-- Do NOT apply in production

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

INSERT INTO redemptions (
    id, tenant_id, idempotency_key, customer_id, campaign_id,
    discount_amount, discount_currency, cart_total, cart_currency,
    store_id, division, redeemed_at, status
) VALUES
(
    '00000000-0000-0000-0000-000000000001',
    'tenant-kroger-001',
    'pos-store001-txn-98765',
    'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
    'cccccccc-cccc-cccc-cccc-cccccccccccc',
    5.00, 'USD', 75.00, 'USD',
    'store001', 'midwest',
    NOW() - INTERVAL '2 hours',
    'CONFIRMED'
),
(
    '00000000-0000-0000-0000-000000000002',
    'tenant-kroger-001',
    'pos-store002-txn-11111',
    'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb',
    'cccccccc-cccc-cccc-cccc-cccccccccccc',
    10.00, 'USD', 120.00, 'USD',
    'store002', 'southeast',
    NOW() - INTERVAL '1 day',
    'CLAIMED'
) ON CONFLICT DO NOTHING;
