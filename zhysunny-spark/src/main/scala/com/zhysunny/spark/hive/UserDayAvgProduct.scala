package com.zhysunny.spark.hive

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

/**
  * 求每个用户平均每个购买天中购买的商品数 【把days_since_prior_order当做一个月中购买的天】
  */
object UserDayAvgProduct {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Hive作业")
      .enableHiveSupport()
      .getOrCreate()
    // 获得表数据
    val user_order_df = spark.sql("select order_id,user_id,days_since_prior_order from badou.orders")
    val order_product_df = spark.sql("select order_id,product_id from badou.order_products_prior")

    user_order_df.join(order_product_df, "order_id")
      .groupBy("user_id", "days_since_prior_order")
      .count()
      .withColumnRenamed("count", "product_cnt")
      .groupBy("user_id")
      .avg("product_cnt")
      .withColumnRenamed("avg(product_cnt)", "product_avg")
      .orderBy(desc("product_avg"))
      .show()


    order_product_df.groupBy("order_id")
      .count()
      .withColumnRenamed("count", "product_cnt")
      .join(user_order_df, "order_id")
      .groupBy("user_id")
      .agg((sum("product_cnt") / countDistinct("days_since_prior_order")).as("product_avg"))
      .orderBy(desc("product_avg"))
      .show()
  }
}
