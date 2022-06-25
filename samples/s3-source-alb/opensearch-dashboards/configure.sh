
echo 'Create Index Pattern'

curl -X POST 'localhost:5601/api/saved_objects/index-pattern/alb_logs' -H 'osd-xsrf: true' -H 'Content-Type: application/json' -u admin:admin -d '
{
  "attributes": {
    "title": "alb_logs*",
    "timeFieldName": "time"
  }
}'

echo ''
echo 'Create Visualization'

curl -X POST 'localhost:5601/api/saved_objects/visualization/alb_logs' -H 'osd-xsrf: true' -H 'Content-Type: application/json' -u admin:admin -d '
{
  "attributes": {
    "title": "Status Codes",
    "visState": "{\"title\":\"Status Codes\",\"type\":\"pie\",\"aggs\":[{\"id\":\"1\",\"enabled\":true,\"type\":\"count\",\"params\":{},\"schema\":\"metric\"},{\"id\":\"2\",\"enabled\":true,\"type\":\"terms\",\"params\":{\"field\":\"elb_status_code.keyword\",\"orderBy\":\"1\",\"order\":\"desc\",\"size\":5,\"otherBucket\":false,\"otherBucketLabel\":\"Other\",\"missingBucket\":false,\"missingBucketLabel\":\"Missing\"},\"schema\":\"segment\"}],\"params\":{\"type\":\"pie\",\"addTooltip\":true,\"addLegend\":true,\"legendPosition\":\"right\",\"isDonut\":true,\"labels\":{\"show\":false,\"values\":true,\"last_level\":true,\"truncate\":100}}}",
    "kibanaSavedObjectMeta": {
      "searchSourceJSON": "{\"query\":{\"query\":\"\",\"language\":\"kuery\"},\"filter\":[],\"indexRefName\":\"kibanaSavedObjectMeta.searchSourceJSON.index\"}"
    }
  },
  "references": [
    {
      "name": "kibanaSavedObjectMeta.searchSourceJSON.index",
      "type": "index-pattern",
      "id": "alb_logs"
    }
  ]
}'
