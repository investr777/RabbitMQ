#!/bin/bash

ENVIRONMENT_DIR=$PWD

cd ../sources/rmq/
./gradlew clean build

cd $ENVIRONMENT_DIR

docker-compose -f docker-compose.yaml build
docker-compose -f docker-compose.yaml up
