#!/bin/bash

theDir=`dirname $0`
path=`cd $theDir;cd ../;pwd`
source $path/conf/badou-env.sh

$SCALA_EXE -cp $JAR_FILE:$CLASSPATH com.admin.scala.FileTest
