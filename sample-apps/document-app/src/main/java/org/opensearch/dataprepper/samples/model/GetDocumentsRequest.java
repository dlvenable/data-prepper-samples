package org.opensearch.dataprepper.samples.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class GetDocumentsRequest {
    private List<String> documentIds;
}
