package com.zhysunny.spark.hive

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

/**
  * 每个用户平均每个订单是多少商品
  */
object UserOrderAvgProduct {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Hive作业")
      .enableHiveSupport()
      .getOrCreate()
    // 获得表数据
    val user_order_df = spark.sql("select order_id,user_id from badou.orders")
    val order_product_df = spark.sql("select order_id,product_id from badou.order_products_prior")

    order_product_df.select("order_id", "product_id").groupBy("order_id")
      .count()
      .withColumnRenamed("count", "product_cnt")
      .join(user_order_df.select("order_id", "user_id"), "order_id")
      .groupBy("user_id")
      .avg("product_cnt")
      .withColumnRenamed("avg(product_cnt)", "product_avg")
      .orderBy(desc("product_avg"))
      .show()
  }
}
