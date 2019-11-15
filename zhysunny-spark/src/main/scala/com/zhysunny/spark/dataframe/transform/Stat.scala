package com.zhysunny.spark.dataframe.transform

import org.apache.spark.sql.SparkSession

object Stat {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("spark基本操作之stat")
      .enableHiveSupport()
      .getOrCreate()

    val df = spark.sql("select order_id,user_id,order_dow from badou.orders").limit(20)
    // order_dow和user_id出现频率超过0.3的元素
    df.stat.freqItems(Seq("order_dow", "user_id"), 0.3).show()

  }
}
