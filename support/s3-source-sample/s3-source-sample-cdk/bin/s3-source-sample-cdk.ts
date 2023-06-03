#!/usr/bin/env node
import 'source-map-support/register';
import * as cdk from 'aws-cdk-lib';
import { S3SourceSampleStack } from '../lib/S3SourceSampleStack';
import { LoadBalancerLogsStack } from '../lib/LoadBalancerLogs';
import {VpcFlowLogs} from "../lib/VpcFlowLogs";
import {DataPrepperAccessStack} from "../lib/DataPrepperAccessStack";

const app = new cdk.App();

let dataPrepperAccessStack = new DataPrepperAccessStack(app, 'DataPrepperAccessStack', {
  env: {
    region: process.env.CDK_DEFAULT_REGION
  }
});

const loadBalancerLogsStack = new LoadBalancerLogsStack(app, 'LoadBalancerLogsStack', {
  dataPrepperRole: dataPrepperAccessStack.dataPrepperRole,
  env: {
    region: process.env.CDK_DEFAULT_REGION
  }
});

let s3SourceSampleStack = new S3SourceSampleStack(app, 'S3SourceSampleStack', {
  accessLogsBucket: loadBalancerLogsStack.accessLogsBucket,
  env: {
    region: process.env.CDK_DEFAULT_REGION
  }
});

new VpcFlowLogs(app, 'SampleVpcFlowLogsStack', {
  dataPrepperRole: dataPrepperAccessStack.dataPrepperRole,
  vpc: s3SourceSampleStack.vpc,
  env: {
    region: process.env.CDK_DEFAULT_REGION
  }
});
