package org.opensearch.dataprepper.samples.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class Document {
    private String documentId;
    private String title;
    private String description;
}
