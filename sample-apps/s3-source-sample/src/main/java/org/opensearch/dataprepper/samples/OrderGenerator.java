package org.opensearch.dataprepper.samples;

import org.opensearch.dataprepper.samples.model.Order;

import java.util.Random;
import java.util.UUID;

public class OrderGenerator {
    private final Random random = new Random();

    public Order randomOrder() {
        return randomOrder(UUID.randomUUID().toString());
    }

    public Order randomOrder(final String orderId) {
        return Order.builder()
                .orderId(orderId)
                .customerId(UUID.randomUUID().toString())
                .productSku(UUID.randomUUID().toString())
                .price(random.nextInt(5000) + 1)
                .address(UUID.randomUUID().toString())
                .build();
    }
}
