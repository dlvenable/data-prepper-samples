# S3 Source

This is a sample for extracting logs from an S3 Source. It uses
an ALB.

### Setup

_TODO: Update this documentation_

Create an S3 bucket for ALB logs. Create an SQS queue. Connect event notifications.

You must deploy a web application using an ALB and then exercise requests
against it.

This can be any web application you want. But, there is a sample available.

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

