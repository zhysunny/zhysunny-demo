#!/bin/bash

theDir=`dirname $0`
path=`cd $theDir;cd ../;pwd`
source $path/conf/badou-env.sh

$EXECUTE \
	--class com.admin.spark.hbase.HbaseWriter \
	--master yarn-cluster \
    --files $HIVE_SITE_PATH \
    --jars $path/lib/hbase-client-1.3.1.jar,$path/lib/hbase-common-1.3.1.jar,$path/lib/hbase-server-1.3.1.jar,$path/lib/hbase-protocol-1.3.1.jar,$path/lib/htrace-core-3.1.0-incubating.jar \
	$JAR_FILE
