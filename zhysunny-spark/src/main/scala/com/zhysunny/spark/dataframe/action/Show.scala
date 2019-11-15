package com.zhysunny.spark.dataframe.action

import org.apache.spark.sql.SparkSession

object Show {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("spark基本操作之show方法")
      .enableHiveSupport()
      .getOrCreate()

    val df = spark.sql("select order_id,user_id,order_dow from badou.orders")
    // 只显示前20条记录。
    df.show
    // 显示numRows条
    df.show(10)
    // 是否最多只显示20个字符，默认为true。
    df.show(false)
    // 综合前面的显示记录条数，以及对过长字符串的显示格式。
    df.show(10, false)
  }
}
