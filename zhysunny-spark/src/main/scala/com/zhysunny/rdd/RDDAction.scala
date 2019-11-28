package com.zhysunny.rdd

import org.apache.spark.sql.SparkSession

/**
  * RDD行动操作
  * @author 章云
  * @date 2019/11/28 21:35
  */
object RDDAction {

  def main(args: Array[String]): Unit = {
    val sc = SparkSession.builder()
      .master("local[2]")
      .appName("RDD行动操作")
      .getOrCreate().sparkContext

    val array = Array(1, 4, 8, 3, 2, 1, 3, 5, 4, 2)
    val rdd = sc.parallelize(array, 3)
    println(rdd.first())
    println(rdd.count())
    println(rdd.reduce(_ + _))
    // rdd转数组
    println(rdd.collect().toBuffer)
    // 不排序，取前几位
    println(rdd.take(10).toBuffer)
    // 默认降序排序，取前几位
    println(rdd.top(3).toBuffer)
    // 默认升序排序，取前几位
    println(rdd.takeOrdered(10).toBuffer)
    // aggregate操作
    println(rdd.foreachPartition(iter => println(iter.toArray.toBuffer)))
    // rdd三个分区分别是ArrayBuffer(3, 2, 1)，ArrayBuffer(1, 4, 8)，ArrayBuffer(3, 5, 4, 2)
    // aggregate操作使用第一个函数对每个分区和zeroValue操作，分别得到7,14,15
    // 再使用第二个函数对上面结果和zeroValue操作，得到结果37
    println(rdd.aggregate(1)({ (x: Int, y: Int) => x + y }, { (x: Int, y: Int) => x + y }))
    // fold和aggregate一样操作，函数相当于两个相同的
    println(rdd.fold(1)({ (x: Int, y: Int) => x + y }))
    // 针对key-value的rdd，找出key对应的所有value值，返回Seq[V]
    println(rdd.map((_, 1)).lookup(1))
    // 统计每个key出现的次数，返回Map[K,Long]
    println(rdd.map((_, 1)).countByKey())
    // foreach  foreachPartition  遍历
    // sortBy 排序
    // 存储文件操作
    rdd.saveAsTextFile("")
    rdd.saveAsObjectFile("") //序列化
    rdd.map((_, 1)).saveAsSequenceFile("")
    rdd.map((_, 1)).saveAsHadoopFile("")
    rdd.map((_, 1)).saveAsHadoopDataset(null)
    rdd.map((_, 1)).saveAsNewAPIHadoopFile("")
    rdd.map((_, 1)).saveAsNewAPIHadoopDataset(null)

  }

}
