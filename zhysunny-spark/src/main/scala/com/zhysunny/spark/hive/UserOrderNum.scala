package com.zhysunny.spark.hive

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

/**
  * 作业1：每个用户有多少订单
  */
object UserOrderNum {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Hive作业")
      .enableHiveSupport()
      .getOrCreate()
    import spark.implicits._
    // 获得表数据
    val user_order_df = spark.sql("select order_id,user_id from badou.orders")

    // rdd操作
    var result = user_order_df.select("user_id", "order_id")
      .rdd.map(x => (x(0).toString, x(1).toString))
      .groupByKey()
      .map(x => (x._1, x._2.size))
      .sortBy(-_._2)
      .toDF
      .withColumnRenamed("_1", "user_id")
      .withColumnRenamed("_2", "order_cnt")
    result.show()

    // dataframe操作
    result = user_order_df.select("user_id", "order_id")
      .groupBy("user_id")
      .count().withColumnRenamed("count", "order_cnt")
      .orderBy(desc("order_cnt"))
    result.show()
  }
}
