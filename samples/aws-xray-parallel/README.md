# AWS X-Ray in Parallel with Data Prepper

Goals:
* Use AWS X-Ray tracing
* Send output to Data Prepper
* Send output to X-Ray

## Usage

Start the necessary tools:

```
docker-compose up -d --build
```


Run the sample apps (each app will run in the foreground and needs its own terminal):

```
./gradlew sample-apps:search-app:bootRun
```

```
./gradlew sample-apps:document-app:bootRun
```



Make cURL requests:

```
curl 'http://localhost:8080/search?q=hello&includeDocument=true'
```


Load Dashboards at [http://localhost:5601/app/observability-dashboards#/trace_analytics/home](http://localhost:5601/app/observability-dashboards#/trace_analytics/home).
Provide `admin` as the username and `admin` as the password.


# Appendix

This section is for documenting steps to run certain commands manually.

Start OpenSearch:

```
docker run -p 9200:9200 -p 9600:9600 -e "discovery.type=single-node" -d opensearchproject/opensearch:1.3.2
```

Start Data Prepper:

```
docker run -p 4900:4900 -p 21890:21890 \
  -v ${PWD}/samples/aws-xray-parallel/data-prepper/pipelines.yaml:/usr/share/data-prepper/pipelines.yaml \
  opensearchproject/data-prepper:latest
```


Start AWS OTel Collector:

```
docker run --rm -p 2000:2000/udp \
  -e AWS_REGION=us-east-1 \
  -e AWS_PROFILE=default \
  -v ~/.aws:/root/.aws \
  -v "${PWD}/samples/aws-xray-parallel/aws-otel-collector/otel-config.yaml":/otel-local-config.yaml \
  --name awscollector public.ecr.aws/aws-observability/aws-otel-collector:latest \
  --config otel-local-config.yaml
```
