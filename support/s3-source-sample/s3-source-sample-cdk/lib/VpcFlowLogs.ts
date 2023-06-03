import * as cdk from 'aws-cdk-lib';
import {Construct} from 'constructs';
import {BlockPublicAccess, Bucket, EventType, IBucket} from "aws-cdk-lib/aws-s3";
import {CfnOutput, RemovalPolicy} from "aws-cdk-lib";
import {Queue} from "aws-cdk-lib/aws-sqs";
import {SqsDestination} from "aws-cdk-lib/aws-s3-notifications";
import {Role} from "aws-cdk-lib/aws-iam";
import {FlowLog, FlowLogDestination, FlowLogResourceType, Vpc} from "aws-cdk-lib/aws-ec2";

interface VpcFlowLogsStackProps extends cdk.StackProps {
  readonly dataPrepperRole: Role;
  readonly vpc: Vpc;
}

export class VpcFlowLogs extends cdk.Stack {
  public readonly flowLogsBucket: IBucket;

  constructor(scope: Construct, id: string, props: VpcFlowLogsStackProps) {
    super(scope, id, props);

    this.flowLogsBucket = new Bucket(this, 'VpcFlowLogsBucket', {
      blockPublicAccess: BlockPublicAccess.BLOCK_ALL,
      removalPolicy: RemovalPolicy.RETAIN,
    });

    new FlowLog(this, 'VpcFlowLog', {
      resourceType: FlowLogResourceType.fromVpc(props.vpc),
      destination: FlowLogDestination.toS3(this.flowLogsBucket)
    });

    const eventQueue = new Queue(this, 'VpcFlowLogEventQueue', {
    });

    this.flowLogsBucket.addEventNotification(EventType.OBJECT_CREATED, new SqsDestination(eventQueue));

    eventQueue.grantConsumeMessages(props.dataPrepperRole);
    this.flowLogsBucket.grantRead(props.dataPrepperRole);

    new CfnOutput(this, 'VpcFlowLogBucketName', {
      value: this.flowLogsBucket.bucketName
    });

    new CfnOutput(this, 'VpcFlowLogEventQueueUrl', {
      value: eventQueue.queueUrl
    });
  }
}
