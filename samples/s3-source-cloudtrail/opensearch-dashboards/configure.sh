
echo 'Create Index Pattern'

curl -X POST 'localhost:5601/api/saved_objects/index-pattern/aws_cloud_trail' -H 'osd-xsrf: true' -H 'Content-Type: application/json' -u admin:admin -d '
{
  "attributes": {
    "title": "aws_cloud_trail*",
    "timeFieldName": "@timestamp"
  }
}'
