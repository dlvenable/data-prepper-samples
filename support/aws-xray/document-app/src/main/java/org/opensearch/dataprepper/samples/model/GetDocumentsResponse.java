package org.opensearch.dataprepper.samples.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetDocumentsResponse {
    private List<Document> documents;
}
