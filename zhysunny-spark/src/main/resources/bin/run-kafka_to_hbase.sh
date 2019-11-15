#!/bin/bash

theDir=`dirname $0`
path=`cd $theDir;cd ../;pwd`
source $path/conf/badou-env.sh

$EXECUTE \
	--class com.admin.streaming.KakfaDirectToHbase \
	--master yarn-cluster \
	--jars $path/lib/hbase-client-1.3.1.jar,$path/lib/hbase-common-1.3.1.jar,$path/lib/hbase-server-1.3.1.jar,$path/lib/hbase-protocol-1.3.1.jar,$path/lib/htrace-core-3.1.0-incubating.jar,$path/lib/metrics-core-2.2.0.jar,$path/lib/zkclient-0.3.jar,$path/lib/kafka_2.11-0.8.2.1.jar,$path/lib/spark-streaming-kafka-0-8_2.11-2.0.0.jar \
	$JAR_FILE \
	"10" "800"