#!/bin/zsh

pushd unaggregated
docker-compose down
popd

echo -n '' > fluent-bit/logs/sample.log
