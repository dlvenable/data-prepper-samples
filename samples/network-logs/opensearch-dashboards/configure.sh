
echo 'Waiting for OpenSearch'

# Wait for OpenSearch
OPENSEARCH_READY=false
echo "Waiting for OpenSearch to start"
until ${OPENSEARCH_READY}
do
    if curl -s -k -u 'admin:admin' 'https://localhost:9200/' > /dev/null
    then
        OPENSEARCH_READY=true
    else
        sleep 0.2
    fi
done

echo "\b "
echo "OpenSearch Dashboards ready."

echo 'Waiting for OpenSearch Dashboards'

# Wait for OpenSearch Dashboards
OPENSEARCH_DASHBOARDS_READY=false
echo "Waiting for OpenSearch Dashboards to start"
until ${OPENSEARCH_DASHBOARDS_READY}
do
    if curl -s -k -u 'admin:admin' -H 'osd-xsrf: true' -H 'Content-Type: application/json' 'http://localhost:5601/app/home' > /dev/null
    then
        OPENSEARCH_DASHBOARDS_READY=true
    else
        sleep 0.2
    fi
done

# The above command concludes before the OSD server is actually ready.
# TODO: Find a better API that determines that OSD is ready to receive API requests.
sleep 5

echo "\b "
echo "OpenSearch Dashboards ready."

echo 'Create Index Pattern'

curl -X POST 'localhost:5601/api/saved_objects/index-pattern/network_activity' -H 'osd-xsrf: true' -H 'Content-Type: application/json' -u admin:admin -d '
{
  "attributes": {
    "title": "network_activity*",
    "timeFieldName": "timestamp"
  }
}'
