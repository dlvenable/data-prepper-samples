import * as cdk from 'aws-cdk-lib';
import {Construct} from 'constructs';
import {BlockPublicAccess, Bucket} from "aws-cdk-lib/aws-s3";
import {CfnOutput, RemovalPolicy} from "aws-cdk-lib";
import {Role} from "aws-cdk-lib/aws-iam";

interface S3SinkStackProps extends cdk.StackProps {
  readonly dataPrepperRole: Role;
}

export class S3SinkStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props: S3SinkStackProps) {
    super(scope, id, props);

    const s3SinkBucket = new Bucket(this, 'S3SinkBucket', {
      blockPublicAccess: BlockPublicAccess.BLOCK_ALL,
      removalPolicy: RemovalPolicy.RETAIN,
    });

    s3SinkBucket.grantWrite(props.dataPrepperRole);

    new CfnOutput(this, 'S3SinkBucketName', {
      value: s3SinkBucket.bucketName
    });
  }
}
