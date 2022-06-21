package org.opensearch.dataprepper.samples.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@Builder
public class ListOrdersResponse {
    private List<Order> orders;
}
