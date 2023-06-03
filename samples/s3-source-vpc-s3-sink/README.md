# S3 source from VPC with S3 sink

This sample will get VPC FlowLogs from S3.

It normalizes the events.
Then all events which originated from outside the VPC (internal network), go to OpenSearch.
Events which originated from inside the VPC will not go to OpenSearch.
All events go to the S3 sink.

## Initial setup

This sample uses the [`s3-source-sample-cdk`](../../support/s3-source-sample/s3-source-sample-cdk).
See instructions there first.

## Priming

Use the [`s3-source-sample-runner`](../../support/s3-source-sample-runner) to load data.

## Run

Update the [`vpc-flow-log-pipeline.yaml`] to use your resource names.

Get AWS credentials and make them available in your `${HOME}/.aws/credentials` file.

Now run to get data:

```
docker-compose up -d 
```
