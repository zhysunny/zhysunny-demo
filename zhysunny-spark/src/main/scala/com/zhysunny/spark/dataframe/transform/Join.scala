package com.zhysunny.spark.dataframe.transform

import org.apache.spark.sql.SparkSession

object Join {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("spark基本操作之join")
      .enableHiveSupport()
      .getOrCreate()

    val df = spark.sql("select order_id,user_id,order_dow from badou.orders")

    val df1 = df.limit(20)
    val df2 = df.limit(20)
    // 400条记录，相当于20个Row和20个Row做笛卡尔积
    df1.join(df2).show(100)
    // 必须是相同的字段名，结果这个字段只会显示一次
    df1.join(df2, "order_id").show()
    // 多字段join，相当于把两个字段看成一个整体
    df1.join(df2, Seq("order_id", "user_id")).show()
    // join类型inner, outer, left_outer, right_outer, leftsemi
    df1.join(df2, Seq("order_id"), "inner").show()
    // 适用于不同的字段名做join，这时两个字段都存在，这里也可以加join类型
    df1.join(df2, df1("user_id") === df2("user_id")).show()

  }
}
