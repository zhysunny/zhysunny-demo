package com.zhysunny.spark.dataframe.action

import org.apache.spark.sql.SparkSession

object Describe {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("spark基本操作之describe方法")
      .enableHiveSupport()
      .getOrCreate()

    val df = spark.sql("select order_id,user_id,order_dow from badou.orders where user_id='1'")
    // 这个方法可以动态的传入一个或多个String类型的字段名，结果仍然为DataFrame对象，
    // 用于统计数值类型字段的统计值，比如count, mean, stddev, min, max等。
    df.describe("order_dow").show()

    //    scala> df.show
    //    +--------+-------+---------+
    //    |order_id|user_id|order_dow|
    //    +--------+-------+---------+
    //    | 2539329|      1|        2|
    //      | 2398795|      1|        3|
    //      |  473747|      1|        3|
    //      | 2254736|      1|        4|
    //      |  431534|      1|        4|
    //      | 3367565|      1|        2|
    //      |  550135|      1|        1|
    //      | 3108588|      1|        1|
    //      | 2295261|      1|        1|
    //      | 2550362|      1|        4|
    //      | 1187899|      1|        4|
    //      +--------+-------+---------+
    //
    //
    //    scala> df.describe("order_dow").show()
    //    +-------+------------------+
    //    |summary|         order_dow|
    //    +-------+------------------+
    //    |  count|                11|
    //      |   mean|2.6363636363636362|
    //      | stddev|1.2862913567871996|
    //      |    min|                 1|
    //      |    max|                 4|
    //      +-------+------------------+
  }
}
