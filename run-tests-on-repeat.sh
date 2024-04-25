#!/usr/bin/env bash

set -eu

REPEAT_COUNT=$1

for ((i=1; i <= REPEAT_COUNT; i++))
do
  echo ""
  echo "### Attempt $i ###"
  echo ""
  ./gradlew connectedDebugAndroidTest
done
