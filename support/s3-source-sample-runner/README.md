# S3 Source Sample Runner

This is a simple script which exercises the [s3-source-sample](../s3-source-sample) API. You can use
it to generate some load on an s3-source-sample app running behind an ALB. This will generate ALB logs that
Data Prepper can read. You can create a Data Prepper cluster per [s3-source-alb](../../samples/s3-source-alb) 
to process those ALB logs.

### Run locally

```
./runner.py {YOUR_BASE_URI}
```

### Run on AWS

The [runner-cdk](runner-cdk) project will deploy several ECS Fargate tasks running the `runner.py` script. You can
use this if you wish to generate a large volume of load.

To use the CDK, first change directories to runner-cdk.

```
cd runner-cdk
```

Then run:

```
cdk synth --context targetUrl={YOUR_BASE_URI}
cdk deploy --all --require-approval never --context targetUrl={YOUR_BASE_URI}
```
