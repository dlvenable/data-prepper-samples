package org.opensearch.dataprepper.samples;

import com.amazonaws.xray.proxies.apache.http.HttpClientBuilder;
import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Service
@XRayEnabled
public class DocumentBackend {

    private final CloseableHttpClient httpclient = HttpClientBuilder.create().build();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Map<String, Object> getDocument(final String documentId) {
        final HttpGet httpGet = new HttpGet("http://localhost:8081/document/" + documentId);

        try {
            return doLoad(httpGet);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, Object> doLoad(final HttpGet httpGet) throws IOException {
        try(final CloseableHttpResponse response = httpclient.execute(httpGet)) {
            final HttpEntity entity = response.getEntity();
            final InputStream responseInputStream = entity.getContent();
            @SuppressWarnings("unchecked")
            final Map<String, Object> document = objectMapper.readValue(responseInputStream, Map.class);
            EntityUtils.consume(entity);
            return document;
        }
    }
}
