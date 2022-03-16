#!/bin/bash

# 1. build flink-cdc-connector

cd ../
#mvn clean package -DskipTests -Dfast

cd flink-cdc-connector-14

# 2. copy target jar packages to this project in libs
cp ../flink-connector-postgres-cdc/target/flink-connector-postgres-cdc-2.2-SNAPSHOT.jar ./libs
cp ../flink-connector-debezium/target/flink-connector-debezium-2.2-SNAPSHOT.jar ./libs

# 3. package project
mvn clean package
