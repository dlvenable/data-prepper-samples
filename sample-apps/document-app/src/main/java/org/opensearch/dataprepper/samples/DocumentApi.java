package org.opensearch.dataprepper.samples;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import lombok.extern.slf4j.Slf4j;
import org.opensearch.dataprepper.samples.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@XRayEnabled
@Slf4j
public class DocumentApi {
    private final DocumentBackend documentBackend;

    @Autowired
    DocumentApi(final DocumentBackend documentBackend) {
        this.documentBackend = documentBackend;
    }

    @GetMapping("document/{documentId}")
    Document getDocument(@PathVariable final String documentId) {
        final Document document = documentBackend.getDocumentById(documentId);

        log.info("Returning: {}", document);

        return document;
    }
}
