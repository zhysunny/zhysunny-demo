#!/bin/bash

theDir=`dirname $0`
path=`cd $theDir;cd ../;pwd`
source /etc/profile

#SPARK_HOME="/data/apps/spark-2.0.2"
HIVE_SITE_PATH="/data/apps/spark-2.0.2/conf/hive-site.xml"
JAR_FILE="$path/badou.jar"
EXECUTE="$SPARK_HOME/bin/spark-submit"
#JAVA_HOME="/home/apps/jdk/jdk8"
JAVA_EXE="$JAVA_HOME/bin/java"
SCALA_EXE="$SCALA_HOME/bin/scala"
