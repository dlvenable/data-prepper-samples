# Prometheus

This Docker compose will run Prometheus and Grafana and configure Prometheus to scrape from Data Prepper.

To run:

```
docker compose up
```

Then open http://localhost:3000

Select "Add you first data source"

Select "Prometheus"

For Connection, enter `http://prometheus:9090`

Press "Save & Test"
