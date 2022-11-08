# S3 Source Sample CDK

CDK application for deploying the S3 Source Sample.

First, build the project using Gradle.

NOTE: You must set `CDK_DEFAULT_REGION` to the same value as the region in your AWS profile.

```
export CDK_DEFAULT_REGION={YOUR_REGION} 
cdk synth --all
cdk deploy --all --require-approval never
```

The `cdk.json` file tells the CDK Toolkit how to execute your app.

## Useful commands

* `npm run build`   compile typescript to js
* `npm run watch`   watch for changes and compile
* `npm run test`    perform the jest unit tests
* `cdk deploy`      deploy this stack to your default AWS account/region
* `cdk diff`        compare deployed stack with current state
* `cdk synth`       emits the synthesized CloudFormation template
