package org.opensearch.dataprepper.samples;

import org.junit.jupiter.api.Test;
import org.opensearch.dataprepper.samples.model.Order;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class OrderGeneratorTest {
    private OrderGenerator createObjectUnderTest() {
        return new OrderGenerator();
    }

    @Test
    void randomOrder() {
        final Order order = createObjectUnderTest().randomOrder();

        assertThat(order, notNullValue());
        assertThat(order.getOrderId(), notNullValue());
    }

    @Test
    void randomOrder_with_orderId() {
        final String orderId = UUID.randomUUID().toString();
        final Order order = createObjectUnderTest().randomOrder(orderId);

        assertThat(order, notNullValue());
        assertThat(order.getOrderId(), equalTo(orderId));
    }
}