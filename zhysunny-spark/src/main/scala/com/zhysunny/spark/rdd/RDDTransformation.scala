package com.zhysunny.spark.rdd

import org.apache.spark.HashPartitioner
import org.apache.spark.sql.SparkSession

/**
  * rdd转换操作
  * @author 章云
  * @date 2019/11/26 22:19
  */
object RDDTransformation {

  def main(args: Array[String]): Unit = {
    val sc = SparkSession.builder()
      .master("local[2]")
      .appName("RDD转换操作")
      .getOrCreate()
      .sparkContext
    // 创建一个rdd
    val array = Array(1, 4, 8, 3, 2, 1, 3, 5, 4, 2, 3, 4)
    val rdd = sc.parallelize(array, 3)
    println(rdd.take(array.length).toBuffer)
    // ArrayBuffer(1, 4, 8, 3, 2, 1, 3, 5, 4, 2, 3, 4)
    // map操作，每个数加1
    println(rdd.map(x => x + 1).take(array.length).toBuffer)
    // ArrayBuffer(2, 5, 9, 4, 3, 2, 4, 6, 5, 3, 4, 5)
    // flatMap操作，每个数和1组成数组
    println(rdd.map(x => Array(x, 1)).flatMap(x => x).take(array.length * 2).toBuffer)
    // ArrayBuffer(1, 1, 4, 1, 8, 1, 3, 1, 2, 1, 1, 1, 3, 1, 5, 1, 4, 1, 2, 1, 3, 1, 4, 1)
    println(rdd.distinct().take(array.length).toBuffer)
    // ArrayBuffer(3, 4, 1, 8, 5, 2)
    // 重分区
    // 重分区大于原分区无效
    rdd.coalesce(5)
    // 重分区小于原分区可以
    rdd.coalesce(1)
    // 重分区大于原分区需要进行shuffle
    rdd.coalesce(5, true)
    // 相当于shuffle的coalesce
    rdd.repartition(5)
    // 随机分组，结果Array[RDD[T]]
    rdd.randomSplit(Array(1, 4, 3, 4))
    // 将每个分区放数组，结果为RDD[Array[T]]
    rdd.glom()
    val other = sc.parallelize(1 to 10, 4)
    // union 并集不去重
    rdd.union(other)
    // intersection 交集去重
    rdd.intersection(other)
    // rdd有other没有的元素，不去重
    rdd.subtract(other)
    // 和map类似，不过是按照分区批量执行
    rdd.mapPartitions(null, true)
    // 和mapPartitions类似，不过需要设置分区索引
    rdd.mapPartitionsWithIndex(null, true)
    // 将相同长度的rdd组装成K-V形式
    rdd.zip(other) //长度不相同抛出异常
    // 键值操作
    // partitionBy 按照K重分区
    rdd.map((_, 1)).partitionBy(new HashPartitioner(2))
    // mapValues  flatMapValues 对V操作
    // combineByKey 将相同key的value组合
    // foldByKey 和 combineByKey类似，方法参数不同
    // reduceByKey,groupByKey 将相同key的value值合并到一个集合
    // reduceByKey先进行combine再进行reduce操作，性能优于groupByKey
    // 连接操作
    // cogroup join fullOuterJoin leftOuterJoin rightOuterJoin subtractByKey
  }

}
