#!/usr/bin/env node
import 'source-map-support/register';
import * as cdk from 'aws-cdk-lib';
import { S3SourceSampleStack } from '../lib/S3SourceSampleStack';
import { LoadBalancerLogsStack } from '../lib/LoadBalancerLogs';

const app = new cdk.App();

const loadBalancerLogsStack = new LoadBalancerLogsStack(app, 'LoadBalancerLogsStack', {
  env: {
    region: process.env.CDK_DEFAULT_REGION
  }
});

new S3SourceSampleStack(app, 'S3SourceSampleStack', {
  accessLogsBucket: loadBalancerLogsStack.accessLogsBucket,
  env: {
    region: process.env.CDK_DEFAULT_REGION
  }
});
