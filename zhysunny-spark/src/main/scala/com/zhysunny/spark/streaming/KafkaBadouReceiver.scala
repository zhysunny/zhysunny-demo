package com.zhysunny.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object KafkaBadouReceiver {
  def main(args: Array[String]): Unit = {
    val group_id = "test"
    val topic = "message_string"
    val exectime = "5"

    val zkHostIP = Array("11", "12", "13").map("192.168.1." + _)

    val ZK_QUORUM = zkHostIP.map(_ + ":2181").mkString(",")
    val numThreads = 2

    //    创建streamContext
    val conf = new SparkConf().setAppName("kafka receiver")
    val ssc = new StreamingContext(conf, Seconds(exectime.toInt))
    //    topic 对应线程Map
    val topicSet = topic.split(",").toSet
    val topicMap = topicSet.map((_, numThreads.toInt)).toMap
    //    通过Receiver接受kafka数据
    // 为什么是map（_._2）: @return DStream of (Kafka message key, Kafka message value)
    val mesR = KafkaUtils.createStream(ssc, ZK_QUORUM, group_id, topicMap).map(_._2)
    mesR.print()

    ssc.start()
    ssc.awaitTermination()
  }
}
