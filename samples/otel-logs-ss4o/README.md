# OTel Logs - Simple Schema for OpenSearch

This sample shows how to collect OpenTelemetry (OTel) logs and ingest them into OpenSearch
in a format that is compatible with Simple Schema for OpenSearch.

This requires a build of the latest Data Prepper 2.11 code which is not yet released.

It uses the [otelgen](https://github.com/krzko/otelgen) project to generate the logs.

## Example output

```
{
"_index": "ss4o_logs",
"_id": "DIi1kZUB6BstTH-s8K1-",
"_score": 1,
"_source": {
  "traceId": "",
  "spanId": "",
  "flags": 0,
  "droppedAttributesCount": 0,
  "serviceName": "otelgen",
  "body": "Log 1: Warn phase: processing",
  "schemaUrl": "https://opentelemetry.io/schemas/1.26.0",
  "timestamp": "2025-03-13T22:52:41.749704254Z",
  "observedTimestamp": "2025-03-13T22:52:41.749704546Z",
  "severity": {
    "text": "Warn",
    "number": 13
  },
  "otel_attributes": {
    "service": {
      "name": "otelgen"
    },
    "host": {
      "name": "node-1"
    },
    "k8s": {
      "namespace": {
        "name": "default"
      },
      "container": {
        "name": "otelgen"
      },
      "pod": {
        "name": "otelgen-pod-b3d8c5ab"
      }
    },
    "http": {
      "method": "DELETE",
      "status_code": 404,
      "target": "/api/v1/resource/1"
    }
  },
  "log.attributes.trace_id": "e94ff9e9930694d6ee9cd08addf24696",
  "log.attributes.worker_id": "1",
  "log.attributes.trace_flags": "01",
  "log.attributes.phase": "processing",
  "instrumentationScope.name": "otelgen",
  "log.attributes.span_id": "ff7415068985f138"
}
```

## To run

```
docker compose up
```

Check in:

```
http://localhost:5601/app/dev_tools#/console
```

