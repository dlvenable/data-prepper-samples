package org.opensearch.dataprepper.samples;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import org.opensearch.dataprepper.samples.model.SearchResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@XRayEnabled
public class SearchApi {

    private final SearchBackend searchBackend;
    private final DocumentBackend documentBackend;

    @Autowired
    SearchApi(final SearchBackend searchBackend,
              final DocumentBackend documentBackend) {
        this.searchBackend = searchBackend;
        this.documentBackend = documentBackend;
    }

    @GetMapping("search")
    public SearchResults performSearch(@RequestParam("q") final String query, @RequestParam(value = "includeDocument", defaultValue = "false") final boolean includeDocument) {
        final SearchResults searchResults = searchBackend.search(query);

        if(includeDocument) {
            final List<SearchResults.Hit> hitsWithDocuments = searchResults.getHits()
                    .stream()
                    .map(SearchResults.Hit::getDocumentId)
                    .map(id -> {
                        final Map<String, Object> document = documentBackend.getDocument(id);
                        return SearchResults.Hit.builder().document(document).documentId(id).build();
                    })
                    .collect(Collectors.toList());
            return SearchResults.builder()
                    .count(searchResults.getCount())
                    .hits(hitsWithDocuments)
                    .build();
        } else {
            return searchResults;
        }
    }
}
