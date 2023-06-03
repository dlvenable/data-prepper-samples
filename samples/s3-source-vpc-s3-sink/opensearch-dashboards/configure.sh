
echo 'Create Index Pattern'

curl -X POST 'localhost:5601/api/saved_objects/index-pattern/vpc_flow_logs' -H 'osd-xsrf: true' -H 'Content-Type: application/json' -u admin:admin -d '
{
  "attributes": {
    "title": "vpc_flow_logs*",
    "timeFieldName": "start"
  }
}'
