# OTLP Pipeline

This sample shows how to collect OpenTelemetry (OTel) signals and ingest them into OpenSearch.

It uses the [otelgen](https://github.com/krzko/otelgen) project to generate the logs.

## To run

```
docker compose up -d
```

Check in:

```
http://localhost:5601/app/dev_tools#/console
```

* Username: `admin`
* Password: `yourStrongPassword123!`

### Troubleshooting

You can check the Data Prepper logs using:

```
docker logs -f data-prepper
```
