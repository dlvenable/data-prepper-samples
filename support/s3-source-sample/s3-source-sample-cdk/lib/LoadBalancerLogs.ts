import * as cdk from 'aws-cdk-lib';
import {Construct} from 'constructs';
import {BlockPublicAccess, Bucket, EventType, IBucket} from "aws-cdk-lib/aws-s3";
import {CfnOutput, RemovalPolicy} from "aws-cdk-lib";
import {Queue} from "aws-cdk-lib/aws-sqs";
import {SqsDestination} from "aws-cdk-lib/aws-s3-notifications";
import {Role} from "aws-cdk-lib/aws-iam";

interface LoadBalancerLogsStackProps extends cdk.StackProps {
  readonly dataPrepperRole: Role;
}

export class LoadBalancerLogsStack extends cdk.Stack {
  public readonly accessLogsBucket: IBucket;


  constructor(scope: Construct, id: string, props: LoadBalancerLogsStackProps) {
    super(scope, id, props);

    const accountId = cdk.Stack.of(this).account;
    const region = cdk.Stack.of(this).region;

    this.accessLogsBucket = new Bucket(this, 'LoadBalancerBucket', {
      bucketName: `${accountId}-${region}-load-balancer`,
      blockPublicAccess: BlockPublicAccess.BLOCK_ALL,
      removalPolicy: RemovalPolicy.RETAIN,
    });

    const eventQueue = new Queue(this, 'LoadBalancerEventQueue', {
      queueName: 'LoadBalancer'
    });

    this.accessLogsBucket.addEventNotification(EventType.OBJECT_CREATED, new SqsDestination(eventQueue));

    eventQueue.grantConsumeMessages(props.dataPrepperRole);
    this.accessLogsBucket.grantRead(props.dataPrepperRole);

    new CfnOutput(this, 'LoadBalancerBucketName', {
      value: this.accessLogsBucket.bucketName
    });

    new CfnOutput(this, 'LoadBalancerEventQueueUrl', {
      value: eventQueue.queueUrl
    });
  }
}
