package com.example.payments;

import java.util.Objects;

/**
 * Adapter: wraps the third-party FastPayClient and makes it conform
 * to the PaymentGateway target interface expected by OrderService.
 */
public class FastPayAdapter implements PaymentGateway {

    private final FastPayClient client;

    public FastPayAdapter(FastPayClient client) {
        this.client = Objects.requireNonNull(client, "FastPayClient must not be null");
    }

    @Override
    public String charge(String customerId, int amountCents) {
        Objects.requireNonNull(customerId, "customerId");
        // FastPayClient uses payNow(custId, amountCents) — we translate here.
        return client.payNow(customerId, amountCents);
    }
}
