package com.zhysunny.spark.hive

import org.apache.spark.sql.SparkSession

import scala.collection.mutable.HashMap

/**
  * 每个用户在一周中的购买订单的分布
  */
object UserOrderDow {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Hive作业")
      .enableHiveSupport()
      .getOrCreate()
    // 获得表数据
    val user_order_df = spark.sql("select user_id,order_dow from badou.orders")
    // dataframe
    user_order_df.selectExpr("user_id", "order_dow")
      .groupBy("user_id")
      .pivot("order_dow")
      .count()
      .withColumnRenamed("0", "dow_0")
      .withColumnRenamed("1", "dow_1")
      .withColumnRenamed("2", "dow_2")
      .withColumnRenamed("3", "dow_3")
      .withColumnRenamed("4", "dow_4")
      .withColumnRenamed("5", "dow_5")
      .withColumnRenamed("6", "dow_6")
      .orderBy("user_id")
      .na.fill(0)
      .show()

    //rdd
    import spark.implicits._
    user_order_df.select("user_id", "order_dow")
      .rdd.map(x => (x(0).toString, (x(1).toString, 1)))
      .groupByKey().mapValues(x => {
      val map = new HashMap[String, Int]();
      map.put("0", 0);
      map.put("1", 0);
      map.put("2", 0);
      map.put("3", 0);
      map.put("4", 0);
      map.put("5", 0);
      map.put("6", 0);
      for (arr <- x) {
        map.put(arr._1, map(arr._1) + 1)
      };
      map.toArray.sortBy(_._1).map(x => x._2);
    }).map(x => (x._1, x._2(0), x._2(1), x._2(2), x._2(3), x._2(4), x._2(5), x._2(6)))
      .toDF("user_id", "dow_0", "dow_1", "dow_2", "dow_3", "dow_4", "dow_5", "dow_6")
      .orderBy("user_id")
      .show()
  }
}
