package com.example.payments;

import java.util.Objects;

/**
 * Adapter: wraps the third-party SafeCashClient (which uses a two-step
 * createPayment + confirm flow) and maps it to the single
 * PaymentGateway.charge() call expected by OrderService.
 */
public class SafeCashAdapter implements PaymentGateway {

    private final SafeCashClient client;

    public SafeCashAdapter(SafeCashClient client) {
        this.client = Objects.requireNonNull(client, "SafeCashClient must not be null");
    }

    @Override
    public String charge(String customerId, int amountCents) {
        Objects.requireNonNull(customerId, "customerId");
        // SafeCashClient's API takes (amount, user) — we reorder and then confirm.
        SafeCashPayment payment = client.createPayment(amountCents, customerId);
        return payment.confirm();
    }
}
