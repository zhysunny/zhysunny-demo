package com.zhysunny.spark.dataframe.transform

import org.apache.spark.sql.SparkSession

object Other {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("spark基本操作之其他操作")
      .enableHiveSupport()
      .getOrCreate()

    val df = spark.sql("select order_id,user_id,order_dow from badou.orders")
    // 计算出两个dataframe相同的记录
    df.limit(20).intersect(df.limit(5)).show()
    // 获取第一个表有第二个表没有的记录
    df.limit(20).except(df.limit(5)).show()
    // 重命名字段名，如果指定的字段名不存在，不进行任何操作
    df.withColumnRenamed("user_id", "id").show()
    // 新增一列order，值为第二个参数Column，可以使用UDF
    df.withColumn("order", df("order_id")).show()
  }
}
