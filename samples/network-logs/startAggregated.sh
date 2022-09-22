#!/bin/zsh

echo -n '' > fluent-bit/logs/sample.log

docker-compose up -d --build

./opensearch-dashboards/configure.sh
