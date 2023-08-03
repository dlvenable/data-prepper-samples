# S3 Source Sample CDK

CDK application for deploying the S3 Source Sample. 
Several of the Data Prepper samples require this stack.

1) Build the project using Gradle. Go the root of the project and run:

```
./gradlew build
```

2) Deploy the CDK stack

**NOTE**: You must set `CDK_DEFAULT_REGION` to the same value as the region in your AWS profile.

```
export CDK_DEFAULT_REGION={YOUR_REGION} 
cdk synth --all
cdk deploy --all --require-approval never
```

### Cleaning up


```
cdk destroy --all
```

You may have to delete the `DataPrepperAccessStack` first.

```
cdk destroy DataPrepperAccessStack
cdk destroy --all
```


## Useful commands (from auto-generated README)

* `npm run build`   compile typescript to js
* `npm run watch`   watch for changes and compile
* `npm run test`    perform the jest unit tests
* `cdk deploy`      deploy this stack to your default AWS account/region
* `cdk diff`        compare deployed stack with current state
* `cdk synth`       emits the synthesized CloudFormation template
