package org.opensearch.dataprepper.samples.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class Order {
    private String orderId;
    private String customerId;
    private String productSku;
    private Integer price;
    private String address;
}
