# S3 source from VPC with S3 sink

This sample will get VPC FlowLogs from S3 and send them to S3 in various formats.

## Initial setup

This sample uses the [`s3-source-sample-cdk`](../../support/s3-source-sample/s3-source-sample-cdk).
See instructions there first.

## Priming

Use the [`s3-source-sample-runner`](../../support/s3-source-sample-runner) to load data.

## Run

Update the [`data-prepper/pipelines/vpc-flow-log-pipeline.yaml`](data-prepper/pipelines/vpc-flow-log-pipeline.yaml) to use your resource names.

Get AWS credentials and make them available in your `${HOME}/.aws/credentials` file.

Now run to get data:

```
docker-compose up -d 
```

Check your S3 bucket for new data.

It should be in `vpc-flow-logs/` and then in a directory under that based on the date of your run.

