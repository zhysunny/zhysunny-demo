package com.zhysunny.spark.dataframe.transform

import org.apache.spark.sql.SparkSession

object Limit {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("spark基本操作之limit")
      .enableHiveSupport()
      .getOrCreate()

    val df = spark.sql("select order_id,user_id,order_dow from badou.orders")
    // limit方法获取指定DataFrame的前n行记录，得到一个新的DataFrame对象。和take与head不同的是，
    // limit方法不是Action操作。
    df.limit(10).show()
  }
}
