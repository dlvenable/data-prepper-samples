import * as cdk from 'aws-cdk-lib';
import {Construct} from 'constructs';
import {DockerImageAsset} from 'aws-cdk-lib/aws-ecr-assets';
import * as path from "path";
import {AwsLogDriver, Cluster, ContainerImage, FargateService, FargateTaskDefinition} from "aws-cdk-lib/aws-ecs";
import {LogGroup, RetentionDays} from "aws-cdk-lib/aws-logs";

export class RunnerStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    const targetUrl: string = scope.node.tryGetContext('targetUrl');
    if(targetUrl === undefined) {
      throw new Error('Must specify the targetUrl.')
    }

    const definedTaskCount: string = scope.node.tryGetContext('taskCount');
    const taskCount = definedTaskCount !== undefined ? parseInt(definedTaskCount) : 2;

    const dockerImage = new DockerImageAsset(this, 'SampleRunner', {
      directory: path.join(__dirname, '../..'),
    });

    const cluster = new Cluster(this, 'SampleRunnerCluster', {
    });

    const sampleRunnerTaskDefinition = new FargateTaskDefinition(this, 'SampleRunnerTaskDefinition', {
      memoryLimitMiB: 512,
      cpu: 256,
    });

    const runnerLogGroup = new LogGroup(this, 'SampleRunnerLogGroup', {
      retention: RetentionDays.ONE_DAY
    });
    sampleRunnerTaskDefinition.addContainer('SampleRunner', {
      image: ContainerImage.fromDockerImageAsset(dockerImage),
      command: [targetUrl],
      logging: new AwsLogDriver({
        logGroup: runnerLogGroup,
        streamPrefix: 's3-source-sample-runner/'
      })
    });

    const sampleRunnerService = new FargateService(this, 'SampleRunnerService', {
      cluster,
      taskDefinition: sampleRunnerTaskDefinition,
      desiredCount: taskCount,
    });
  }
}
