package com.zhysunny.spark.rdd

import org.apache.spark.sql.SparkSession
import scala.collection.immutable.Range.Inclusive

/**
  * rdd创建操作
  * @author 章云
  * @date 2019/11/26 22:19
  */
object RDDCreate {

  def main(args: Array[String]): Unit = {
    val sc = SparkSession.builder()
      .master("local[2]")
      .appName("RDD创建操作")
      .getOrCreate()
      .sparkContext
    // 并行集合
    val array = 1 to 10
    val array2 = 11 to 20
    // parallelize方法
    var para = sc.parallelize(array)
    println(para.partitions.size)
    para = sc.parallelize(array, 5)
    println(para.partitions.size)
    // makeRDD与parallelize方法一直
    para = sc.makeRDD(array, 6)
    println(para.partitions.size)
    // makeRDD设置首选位置
    val collect = Seq[(Inclusive, Seq[String])]((array, Seq[String]("master", "slave1")), (array2, Seq[String]("slave2", "slave3")))
    val rdd = sc.makeRDD(collect)
    println(rdd.partitions.size)
    println(rdd.preferredLocations(rdd.partitions(0)))
    println(rdd.preferredLocations(rdd.partitions(1)))
    // 外部存储资源
    var fileRdd = sc.textFile("README.md")
    println(fileRdd.count()) // 每一行记录作为rdd的一个存储单元
    println(fileRdd.partitions.size)
//    sc.wholeTextFiles()
//    sc.sequenceFile()
//    sc.hadoopFile()
//    sc.newAPIHadoopFile()
//    sc.hadoopRDD()
//    sc.newAPIHadoopRDD()
  }

}
