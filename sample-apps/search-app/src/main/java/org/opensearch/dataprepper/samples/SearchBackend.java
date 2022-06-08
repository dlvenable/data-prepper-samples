package org.opensearch.dataprepper.samples;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import lombok.extern.slf4j.Slf4j;
import org.opensearch.dataprepper.samples.model.SearchResults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@XRayEnabled
@Slf4j
public class SearchBackend {
    public SearchResults search(final String query) {
        final List<SearchResults.Hit> hits = IntStream.range(0, 5)
                .mapToObj(i -> SearchResults.Hit
                        .builder()
                        .documentId(UUID.randomUUID().toString())
                        .build())
                .collect(Collectors.toList());

        final SearchResults searchResults = SearchResults.builder()
                .count(500)
                .hits(hits)
                .build();
        log.info("Returning search results: {}", searchResults);

        return searchResults;
    }
}
