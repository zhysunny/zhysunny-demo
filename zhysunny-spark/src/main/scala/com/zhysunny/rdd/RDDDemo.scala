package com.zhysunny.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkContext}
import org.apache.spark.sql.SparkSession

/**
  * rdd编程接口
  * @author 章云
  * @date 2019/11/25 21:52
  */
object RDDDemo {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local[2]")
      .appName("rdd编程接口")
      .getOrCreate()
    val sc: SparkContext = spark.sparkContext
    // RDD分区
    var rdd: RDD[String] = sc.textFile("README.md")
    println(rdd.partitions.size) //2,默认是CPU核个数
    rdd = sc.textFile("README.md", 6)
    println(rdd.partitions.size) //6
//    rdd.foreach(rdd => println(rdd))
    // RDD首选位置
    val dep = rdd.dependencies(0).rdd
    println(dep.preferredLocations(dep.partitions(0)))
    // RDD依赖关系
    val wordMap = rdd.flatMap(str => str.split(" ")).map(word => (word, 1))
    // 窄依赖
    wordMap.dependencies.foreach(dep => {
      println("dependency type:" + dep.getClass);
      println("dependency rdd:" + dep.rdd);
      println("dependency partitions:" + dep.rdd.partitions);
      println("dependency partitions size:" + dep.rdd.partitions.size);
    })
    //    dependency type:class org.apache.spark.OneToOneDependency
    //    dependency rdd:MapPartitionsRDD[14] at flatMap at <console>:25
    //    dependency partitions:[Lorg.apache.spark.Partition;@30625f51
    //    dependency partitions size:6
    // 宽依赖
    val wordReduce = wordMap.reduceByKey(_ + _)
    wordReduce.dependencies.foreach(dep => {
      println("dependency type:" + dep.getClass);
      println("dependency rdd:" + dep.rdd);
      println("dependency partitions:" + dep.rdd.partitions);
      println("dependency partitions size:" + dep.rdd.partitions.size);
    })
    //    dependency type:class org.apache.spark.ShuffleDependency
    //    dependency rdd:MapPartitionsRDD[15] at map at <console>:25
    //    dependency partitions:[Lorg.apache.spark.Partition;@30625f51
    //    dependency partitions size:6
    // RDD分区计算
    val part = sc.parallelize(1 to 9, 3)

    def iterfunc[T](iter: Iterator[T]): Iterator[(T, T)] = {
      var result = List[(T, T)]()
      var pre: T = iter.next()
      while (iter.hasNext) {
        val cur: T = iter.next()
        result ::= (pre, cur)
        pre = cur
      }
      result.iterator
    }
    // 因为分区的缘故，没有(3,4),(6,7)
    val tuples = part.mapPartitions(iterfunc).collect()
    println(tuples.toBuffer)
    // Array[(Int, Int)] = Array((2,3), (1,2), (5,6), (4,5), (8,9), (7,8))
    // RDD分区函数
    rdd = sc.textFile("README.md", 6)
    println(rdd.partitioner) //None
    val groupRdd = rdd.map(x=>(x,x)).groupByKey(new HashPartitioner(4))
    println(groupRdd.partitioner)
    // Option[org.apache.spark.Partitioner] = Some(org.apache.spark.HashPartitioner@4)
  }

}
