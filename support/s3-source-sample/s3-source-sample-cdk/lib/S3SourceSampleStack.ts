import * as cdk from 'aws-cdk-lib';
import { Construct } from 'constructs';
import * as path from "path";
import {DockerImageAsset} from "aws-cdk-lib/aws-ecr-assets";
import {AwsLogDriver, Cluster, ContainerImage} from "aws-cdk-lib/aws-ecs";
import {ApplicationLoadBalancedFargateService} from "aws-cdk-lib/aws-ecs-patterns";
import {ApplicationLoadBalancer} from "aws-cdk-lib/aws-elasticloadbalancingv2";
import {Vpc} from "aws-cdk-lib/aws-ec2";
import {IBucket} from "aws-cdk-lib/aws-s3";
import {CfnOutput} from "aws-cdk-lib";

interface S3SourceSampleStackProps extends cdk.StackProps {
  readonly accessLogsBucket: IBucket;
}

export class S3SourceSampleStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props: S3SourceSampleStackProps) {
    super(scope, id, props);

    const dockerImage = new DockerImageAsset(this, 'S3SourceSampleImage', {
      directory: path.join(__dirname, '../..'),
    });

    const vpc = new Vpc(this, 'Vpc');

    const cluster = new Cluster(this, 'S3SourceSampleCluster', {
      vpc: vpc
    });

    const loadBalancer = new ApplicationLoadBalancer(this, 'LoadBalancer', {
      vpc: vpc,
      internetFacing: true
    });

    loadBalancer.logAccessLogs(props.accessLogsBucket)

    new ApplicationLoadBalancedFargateService(this, 'S3SourceSampleFargateService', {
      cluster: cluster,
      memoryLimitMiB: 1024,
      cpu: 256,
      desiredCount: 2,
      taskImageOptions: {
        image: ContainerImage.fromDockerImageAsset(dockerImage),
        containerPort: 8080,
        logDriver: new AwsLogDriver({
          streamPrefix: 's3-source-sample/'
        })
      },
      publicLoadBalancer: true,
      loadBalancer: loadBalancer,
    });

    new CfnOutput(this, 'LoadBalancerDnsName', {
      value: loadBalancer.loadBalancerDnsName
    });

  }
}
