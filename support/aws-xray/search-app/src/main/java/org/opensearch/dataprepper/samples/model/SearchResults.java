package org.opensearch.dataprepper.samples.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@ToString
@Builder
public class SearchResults {
    private int count;
    private List<Hit> hits;

    @Getter
    @ToString
    @Builder
    public static class Hit {
        private String documentId;
        private Map<String, Object> document;
    }
}
