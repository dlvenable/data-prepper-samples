# Network Logs Aggregation Sample

Demonstrates aggregation of a network request which has multiple logs.

Setup:

```
echo -n '' > fluent-bit/logs/sample.log && docker-compose up -d --build && ./opensearch-dashboards/configure.sh
```

Write some logs lines:

```
./network-runner.py
```

Check Data Prepper logs for activity:

```
docker logs -f -n 20 data-prepper
```


Load OpenSearch Dashboards for past few hours:

```
http://localhost:5601/app/discover#/?_g=(filters:!(),refreshInterval:(pause:!t,value:0),time:(from:now-8h,to:now))&_a=(columns:!(_source),filters:!(),index:network_activity,interval:auto,query:(language:kuery,query:''),sort:!())
```

Clean-up:

```
docker-compose down && echo -n '' > fluent-bit/logs/sample.log
```
