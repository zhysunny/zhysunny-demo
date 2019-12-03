package com.zhysunny.spark.streaming

import com.zhysunny.util.ColumnUtils
import com.zhysunny.common.date.DateUtils
import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.mapred.JobConf
import org.apache.spark.rdd.RDD

import scala.collection.mutable
import scala.util.Random

class ImportHbaseThread(val rdd: RDD[(String, String)], val jobConf: JobConf) extends Thread {

  override def run(): Unit = {
    // 保存到Hbase
    val random = new Random()
    val set = new mutable.HashSet[String]()
    rdd.map(_._2.split("\t")).map(rowdata => {
      val capture_time = rowdata(0)
      val dt = DateUtils.getStringOfLong(capture_time.toLong * 1000, "yyyyMMdd")
      var rd = random.nextInt(10)
      var rowkey = rd + "_" + dt + "_" + capture_time + "_" + rowdata(2) + "_" + rowdata(7)
      while (set.contains(rowkey)) {
        rd = (random.nextDouble() * 100000).toInt
        rowkey = rd + "_" + dt + "_" + capture_time + "_" + rowdata(2) + "_" + rowdata(7)
      }
      set.add(rowkey)
      val put = new Put(Bytes.toBytes(rowkey))
      val list = ColumnUtils.getColumns()
      for (i <- 0 until list.size) {
        if (rowdata(i).trim().length() > 0) {
          put.addColumn(Bytes.toBytes("MEM"), Bytes.toBytes(list(i)), Bytes.toBytes(rowdata(i)))
        }
      }
      (new ImmutableBytesWritable(), put)
    }).saveAsHadoopDataset(jobConf)
  }

}
