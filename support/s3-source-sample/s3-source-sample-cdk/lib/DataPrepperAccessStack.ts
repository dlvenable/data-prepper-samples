import * as cdk from 'aws-cdk-lib';
import {Construct} from 'constructs';
import {CfnOutput} from "aws-cdk-lib";
import {AccountPrincipal, Role} from "aws-cdk-lib/aws-iam";

interface DataPrepperAccessStackProps extends cdk.StackProps {
}

export class DataPrepperAccessStack extends cdk.Stack {
  public readonly dataPrepperRole: Role;

  constructor(scope: Construct, id: string, props: DataPrepperAccessStackProps) {
    super(scope, id, props);

    const accountId = cdk.Stack.of(this).account;
    const region = cdk.Stack.of(this).region;

    this.dataPrepperRole = new Role(this, 'DataPrepperRole', {
      assumedBy: new AccountPrincipal(accountId)
    });

    new CfnOutput(this, 'DataPrepperRoleArn', {
      value: this.dataPrepperRole.roleArn
    });
  }
}
