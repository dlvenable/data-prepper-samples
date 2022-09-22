#!/bin/zsh

echo -n '' > fluent-bit/logs/sample.log

pushd unaggregated
docker-compose up -d --build
popd

./opensearch-dashboards/configure.sh
