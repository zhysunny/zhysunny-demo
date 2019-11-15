package com.zhysunny.spark.dataframe.transform

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object GroupBy {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("spark基本操作之分组")
      .enableHiveSupport()
      .getOrCreate()

    val df = spark.sql("select order_id,user_id,order_dow from badou.orders")
    // 返回org.apache.spark.sql.RelationalGroupedDataset
    val rgd = df.limit(20).selectExpr("cast(user_id as int)", "order_dow").groupBy("order_dow")
    // 该方法得到的是GroupedData类型对象，在GroupedData的API中提供了group by之后的操作，比如，
    // max(colNames: String*)方法，获取分组中指定字段或者所有的数字类型字段的最大值，只能作用于数字型字段
    // min(colNames: String*)方法，获取分组中指定字段或者所有的数字类型字段的最小值，只能作用于数字型字段
    // mean(colNames: String*)方法(或者avg)，获取分组中指定字段或者所有的数字类型字段的平均值，只能作用于数字型字段
    // sum(colNames: String*)方法，获取分组中指定字段或者所有的数字类型字段的和值，只能作用于数字型字段
    // count()方法，获取分组中的元素个数
    rgd.max("user_id").show()
    rgd.min("user_id").show()
    rgd.mean("user_id").show()
    rgd.avg("user_id").show()
    rgd.sum("user_id").show()
    rgd.count().show()

    rgd.agg(
      max("user_id"),
      min("user_id"),
      mean("user_id"),
      avg("user_id"),
      sum("user_id")
    ).show()

    rgd.agg(
      "user_id" -> "max",
      "user_id" -> "min",
      "user_id" -> "mean",
      "user_id" -> "avg",
      "user_id" -> "sum"
    ).show()

    // 透视
    // 相当于行转列操作，把每行user_id不一样的值作为字段，最后做聚合操作
    // 列转行使用explode，将字段值为数组或列表转成多行
    val pivot = rgd.pivot("user_id")
      .count()
      .withColumnRenamed("1", "id_1")
      .withColumnRenamed("2", "id_2")
    //    +---------+----+----+
    //    |order_dow|   1|   2|
    //      +---------+----+----+
    //    |        2|   2|   5|
    //      |        3|   2|   1|
    //      |        4|   4|null|
    //      |        1|   3|   2|
    //      |        5|null|   1|
    //      +---------+----+----+


    // 逆透视，暂不清楚
    pivot.selectExpr("order_dow", "stack(2,id_1,id_1,id_2,id_2) as (user_id,count)")
      .show()

  }
}
