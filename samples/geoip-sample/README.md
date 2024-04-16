# GeoIP Sample

This shows a sample of using Data Prepper to get geographic location from Apache log requests.



```
cd samples/geoip-sample
```

```
docker compose up
```

After the project is running, navigate in your browser to `http://localhost:5601`. The username and password are `admin` and `admin`.

Go to: http://localhost:5601/app/maps-dashboards/create

* Press "Add Layer"
* Select "Documents"
* For Data, choose:
* * "Data Source" - `apache_geoip*`
* * "Geospatial field" - `client_location`
* Press `Update`
