package com.zhysunny.spark.hive

import org.apache.spark.sql.SparkSession

/**
  * 每个用户最喜爱购买的三个product是什么，最终表结构可以是3个列，或者一个字符串
  */
object UserProductTop3 {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Hive作业")
      .enableHiveSupport()
      .getOrCreate()
    // 获得表数据
    val user_order_df = spark.sql("select order_id,user_id from badou.orders")
    val order_product_df = spark.sql("select order_id,product_id from badou.order_products_prior")

    import spark.implicits._

    user_order_df.join(order_product_df, "order_id")
      .groupBy("user_id", "product_id")
      .count()
      .withColumnRenamed("count", "product_cnt")
      .rdd.map(x => (x(0).toString, (x(1).toString, x(2).toString)))
      .groupByKey().mapValues(x => x.toArray.sortBy(_._2.toInt).reverse.slice(0, 3))
      .flatMapValues(x => x)
      .toDF("user_id", "product")
      .selectExpr("user_id", "product._1 as product_id", "product._2 as product_cnt")
      .show()

    user_order_df.join(order_product_df, "order_id")
      .groupBy("user_id", "product_id")
      .count()
      .withColumnRenamed("count", "product_cnt")
      .rdd.map(x => (x(0).toString, (x(1).toString, x(2).toString)))
      .groupByKey().mapValues(x => x.toArray.sortBy(_._2.toInt).reverse.slice(0, 3).map(x => x._1 + "_" + x._2))
      .toDF("user_id", "product_top3")
      .show(false)

  }
}
