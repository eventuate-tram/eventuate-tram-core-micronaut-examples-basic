#! /bin/bash

set -e

export docker="./gradlew ${DATABASE}${MODE}Compose"

${docker}Build
${docker}Up

./wait-for-services.sh $DOCKER_HOST_IP 8099
./gradlew :eventuate-tram-examples-jdbc-${BROKER}:cleanTest :eventuate-tram-examples-jdbc-${BROKER}:test

${docker}Down