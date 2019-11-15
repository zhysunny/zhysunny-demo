package com.zhysunny.spark.dataframe.transform

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object OrderBySort {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("spark基本操作之排序")
      .enableHiveSupport()
      .getOrCreate()

    val df = spark.sql("select order_id,user_id,order_dow from badou.orders")

    // 默认升序
    df.limit(20).orderBy("order_dow").show()
    // 降序，用-号只能操作数值型
    // 下面两种可以用col() df.apply() 方法代替
    df.limit(20).orderBy(-df("order_dow").cast("double")).show()
    df.limit(20).orderBy(df("order_dow").desc).show()
    // UDF
    df.limit(20).orderBy(desc("order_dow")).show()
    // sort和orderby用法一样
    df.limit(20).sort(df.apply("order_dow").desc).show()

    // 和上面的sort方法功能类似，区别在于sortWithinPartitions方法返回的是按Partition排好序的DataFrame对象。
    df.limit(20).sortWithinPartitions("order_dow").show()
  }
}
