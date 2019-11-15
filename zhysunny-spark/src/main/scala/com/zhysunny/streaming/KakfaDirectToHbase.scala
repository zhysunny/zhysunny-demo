package com.zhysunny.streaming

import java.util.concurrent.{LinkedBlockingQueue, ThreadPoolExecutor, TimeUnit}

import kafka.serializer.StringDecoder
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.mapred.TableOutputFormat
import org.apache.hadoop.mapred.JobConf
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable
import scala.util.Random

object KakfaDirectToHbase {
  def main(args: Array[String]): Unit = {
    val Array(exectime, batch) = args
    val group_id = "test"
    val topic = "message_string"
    val zookeeper = "master:2181,slave1:2181,slave2:2181"

    //    创建streamContext
    val sconf = new SparkConf().set("spark.streaming.kafka.maxRatePerPartition", batch)
    val ssc = new StreamingContext(sconf, Seconds(exectime.toInt))
    val topicSet = topic.split(",").toSet
    val kafkaParam = Map[String, String](
      "metadata.broker.list" -> "master:9092,slave1:9092,slave2:9092",
      "key.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
      "value.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
      "refresh.leader.backoff.ms" -> "5000",
      //      "auto.offset.reset" -> "largest", //读取最新数据(默认)
      "auto.offset.reset" -> "smallest", //从头开始消费
      "zookeeper.connect" -> zookeeper,
      "group.id" -> group_id
    )

    val hconf = HBaseConfiguration.create()
    hconf.set("hbase.zookeeper.quorum", "master,slave1,slave2")
    hconf.set("hbase.zookeeper.property.clientPort", "2181")
    hconf.set("zookeeper.znode.parent", "/hbase")
    val tableName = "JZ_RESOURCE_MEM"

    val jobConf = new JobConf(hconf)
    jobConf.setOutputFormat(classOf[TableOutputFormat])
    jobConf.set(TableOutputFormat.OUTPUT_TABLE, tableName)

    val random = new Random()
    val set = new mutable.HashSet[String]()
    val executor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue[Runnable](100))
    executor.prestartAllCoreThreads()
    val manager = new KafkaManager(kafkaParam)
    val dStream = manager.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, topicSet)
    // 保存到Hbase
    dStream.foreachRDD(rdd => {
      val count = rdd.count().toInt
      println(count)
      if (count > 0) {
        executor.execute(new ImportHbaseThread(rdd, jobConf))
        manager.updateZKOffsets(rdd)
      }
      println("getActiveCount：" + executor.getActiveCount())
      println("getCorePoolSize：" + executor.getCorePoolSize())
      println("getCompletedTaskCount：" + executor.getCompletedTaskCount())
      println("getTaskCount：" + executor.getTaskCount())
    })
    ssc.start()
    ssc.awaitTermination()
  }
}