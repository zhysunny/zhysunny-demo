package com.zhysunny.spark.dataframe.action

import org.apache.spark.sql.SparkSession

object Collect {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("spark基本操作之collect方法")
      .enableHiveSupport()
      .getOrCreate()

    val df = spark.sql("select order_id,user_id,order_dow from badou.orders where user_id='1'")
    // 将dataframe中的所有数据都获取到，并返回一个Array对象。
    df.collect()
    //    scala> df.collect
    //    res20: Array[org.apache.spark.sql.Row] = Array([2539329,1,2], [2398795,1,3], [473747,1,3], [2254736,1,4], [431534,1,4], [3367565,1,2], [550135,1,1], [3108588,1,1], [2295261,1,1], [2550362,1,4], [1187899,1,4])
    //
    //    scala> df.collect()
    //    res21: Array[org.apache.spark.sql.Row] = Array([2539329,1,2], [2398795,1,3], [473747,1,3], [2254736,1,4], [431534,1,4], [3367565,1,2], [550135,1,1], [3108588,1,1], [2295261,1,1], [2550362,1,4], [1187899,1,4])
    // 功能和collect类似，只不过将返回结构变成了List对象
    df.collectAsList()
  }
}
