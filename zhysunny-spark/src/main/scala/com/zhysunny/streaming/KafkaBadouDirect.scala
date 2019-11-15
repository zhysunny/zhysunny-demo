package com.zhysunny.streaming

import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object KafkaBadouDirect {
  def main(args: Array[String]): Unit = {
    val group_id = "test"
    val topic = "message_string"
    val exectime = "5"

    val zkHostIP = Array("11", "12", "13").map("192.168.1." + _)

    val ZK_QUORUM = zkHostIP.map(_ + ":2181").mkString(",")
    val numThreads = 2

    //    创建streamContext
    val conf = new SparkConf().setAppName("kafka direct")
    val ssc = new StreamingContext(conf, Seconds(exectime.toInt))
    //    topic 对应线程Map
    val topicSet = topic.split(",").toSet
    val kafkaParam = Map[String, String](
      "metadata.broker.list" -> "master:9092,slave1:9092,slave2:9092",
      "key.serializer" -> "value.serializer",
      "refresh.leader.backoff.ms" -> "5000",
      "auto.offset.reset" -> "largest", //读取最新数据(默认)
      //      "auto.offset.reset" -> "smallest",//从头开始消费
      "zookeeper.connect" -> ZK_QUORUM,
      "group.id" -> group_id
    )

    //    通过Direct接受kafka数据
    // 为什么是map（_._2）: @return DStream of (Kafka message key, Kafka message value)

    // kafka参数
    val manager = new KafkaManager(kafkaParam)
    val messageDstream = manager.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, topicSet)

    // 保存到Hbase
    messageDstream.foreachRDD(rdd => {
      println(rdd.count())
    })
    //    messageDstream.map(_._2).saveAsObjectFiles("/opt/file/pre", "bcp");
    //    messageDstream.map(_._2).saveAsTextFiles("/opt/file/pre", "bcp");
    // 更新offsets
        messageDstream.foreachRDD(rdd => manager.updateZKOffsets(rdd))

    ssc.start()
    ssc.awaitTermination()
  }

}
