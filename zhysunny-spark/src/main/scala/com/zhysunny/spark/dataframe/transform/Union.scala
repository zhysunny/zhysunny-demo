package com.zhysunny.spark.dataframe.transform

import org.apache.spark.sql.SparkSession

object Union {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("spark基本操作之组合")
      .enableHiveSupport()
      .getOrCreate()

    val df = spark.sql("select order_id,user_id,order_dow from badou.orders")
    // 组合两个dataframe，不做去重(oracle中的union会做去重)
    df.limit(20).union(df.limit(20)).show(40)
    // 效果和union一样
    df.limit(20).unionAll(df.limit(20)).show(40)
  }
}
