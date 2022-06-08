package org.opensearch.dataprepper.samples;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import org.opensearch.dataprepper.samples.model.Document;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@XRayEnabled
public class DocumentBackend {
    public Document getDocumentById(final String documentId) {
        return Document.builder()
                .documentId(documentId)
                .title(UUID.randomUUID().toString())
                .description(UUID.randomUUID().toString())
                .build();
    }
}
