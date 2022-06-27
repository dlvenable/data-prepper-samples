# S3 Source - CloudTrail

Sample project which extracts CloudTrail events.

### Setup

_TODO: Update this documentation_

Enable CloudTrail to write to an S3 bucket. Create an SQS queue. Connect event notifications.

Using the AWS Console should start to create trails.

### Running

1. Update the pipelines.yaml with your resources.

* QUEUE_URL
* STS_ROLE_ARN

2. Get AWS credentials in your `${HOME}/.aws` directory.

3. Run Docker compose

```
docker-compose up -d --build
```

4. Load OpenSearch Dashboards

```
http://localhost:5601
```

