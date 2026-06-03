-- Seed data for local development and integration tests only
-- Do NOT apply in production

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
