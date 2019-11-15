package com.zhysunny.spark.dataframe.transform

import org.apache.spark.sql.SparkSession

object Distinct {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("spark基本操作之去重")
      .enableHiveSupport()
      .getOrCreate()

    val df = spark.sql("select order_id,user_id,order_dow from badou.orders")
    // 返回当前DataFrame中不重复的Row记录。该方法和接下来的dropDuplicates()方法不传入指定字段时的结果相同。
    df.limit(20).select("user_id", "order_dow").distinct().show()
    // 根据指定字段去重。类似于select distinct a, b操作
    df.limit(20).select("user_id", "order_dow").dropDuplicates("user_id").show()
  }
}
