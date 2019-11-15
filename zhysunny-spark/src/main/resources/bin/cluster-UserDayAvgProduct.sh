#!/bin/bash

theDir=`dirname $0`
path=`cd $theDir;cd ../;pwd`
source $path/conf/badou-env.sh

$EXECUTE \
	--class com.admin.spark.hive.UserDayAvgProduct \
	--master yarn-cluster \
    --files $HIVE_SITE_PATH \
    $JAR_FILE
