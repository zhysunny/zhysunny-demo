package com.zhysunny.spark.dataframe.transform

import org.apache.spark.sql.SparkSession

object WhereFilter {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("spark基本操作之过滤")
      .enableHiveSupport()
      .getOrCreate()

    val df = spark.sql("select order_id,user_id,order_dow from badou.orders")

    // 传入筛选条件表达式，可以用and和or。得到DataFrame类型的返回结果
    df.where("user_id=1 or order_dow=0").show()
    // 和where一样
    df.filter("user_id=1 or order_dow=0").show()

  }
}
