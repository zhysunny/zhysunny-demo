package com.zhysunny.rdd

import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel

/**
  * RDD控制操作
  * @author 章云
  * @date 2019/11/27 23:06
  */
object RDDControl {

  def main(args: Array[String]): Unit = {
    val sc = SparkSession.builder()
      .master("local[2]")
      .appName("RDD控制操作")
      .getOrCreate()
      .sparkContext

    val rdd = sc.parallelize(1 to 10, 3)
    rdd.persist()
    rdd.persist(StorageLevel.MEMORY_ONLY)
    // cache 默认StorageLevel.MEMORY_ONLY
    rdd.cache()
    rdd.unpersist()
    sc.setCheckpointDir("")
    rdd.checkpoint()
  }

}
