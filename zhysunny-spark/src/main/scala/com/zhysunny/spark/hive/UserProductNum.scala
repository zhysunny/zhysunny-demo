package com.zhysunny.spark.hive

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

/**
  * 统计每个用户购买过多少个商品
  */
object UserProductNum {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Hive作业")
      .enableHiveSupport()
      .getOrCreate()
    // 获得表数据
    val user_order_df = spark.sql("select order_id,user_id from badou.orders")
    val order_product_df = spark.sql("select order_id,product_id from badou.order_products_prior")

    user_order_df.select("order_id", "user_id")
      .join(order_product_df.select("order_id", "product_id"), "order_id")
      .groupBy("user_id")
      .count().withColumnRenamed("count", "product_cnt")
      .orderBy(desc("product_cnt"))
      .show()
  }
}
