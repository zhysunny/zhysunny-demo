package com.zhysunny.spark.hbase

import org.apache.hadoop.hbase.{HBaseConfiguration}
import org.apache.hadoop.hbase.client.{HTable, Put}
import org.apache.hadoop.hbase.mapred.TableOutputFormat
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.mapred.JobConf
import org.apache.spark.sql.SparkSession

object HbaseWriter {
  def main(args: Array[String]): Unit = {
    // 从hive取数据Dataframe->RDD写入hbase
    //    HBase zookeeper
    val ZOOKEEPER_QUORUM = "192.168.1.11,192.168.1.12,192.168.1.13"
    val spark = SparkSession
      .builder()
      .appName("Spark to Hbase")
      .enableHiveSupport()
      .getOrCreate()

    val user_order_df = spark.sql("select order_id,user_id,order_dow from badou.orders limit 20")

    /**
      * 一个put对象就是一行记录，在构造方法中指定主键user_id
      * 所有插入的数据必须用org.apache.hadoop.hbase.util.toBytes方法转换
      **/
    user_order_df.rdd.map { row =>
      val order_id = row(0).asInstanceOf[String]
      val user_id = row(1).asInstanceOf[String]
      val order_dow = row(2).asInstanceOf[String]
      var p = new Put(Bytes.toBytes(user_id))
      // 列族，列名，value
      p.addColumn(Bytes.toBytes("id"), Bytes.toBytes("order"), Bytes.toBytes(order_id))
      p.addColumn(Bytes.toBytes("num"), Bytes.toBytes("dow"), Bytes.toBytes(order_dow))
      p
    }.foreachPartition { partition =>
      // foreachPartition属于批处理
      // 初始化jobconf,TableOutputFormat必须在org.apache.hadoop.hbase.mapred包下
      val jobConf = new JobConf(HBaseConfiguration.create())
      jobConf.set("hbase.zookeeper.quorum", ZOOKEEPER_QUORUM)
      jobConf.set("hbase.zookeeper.property.clientPort", "2181")
      jobConf.set("zookeeper.znode.parent", "/hbase")
      jobConf.setOutputFormat(classOf[TableOutputFormat])
      //写入的表名
      val table = new HTable(jobConf, "orders")
      //把
      import scala.collection.JavaConversions._
      table.put(seqAsJavaList(partition.toSeq))
    }
  }
}
