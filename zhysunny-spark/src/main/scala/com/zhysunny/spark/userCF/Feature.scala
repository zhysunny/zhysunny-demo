package com.zhysunny.spark.userCF

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

object Feature {
  def getFeature(priors: DataFrame, orders: DataFrame): (DataFrame, DataFrame) = {
    // 产品特征
    //    1.销售量 prod_cnt
    //    2.商品被再次购买（reordered）量prod_sum_rod
    //    3.统计reordered比率 prod_rod_rate (重复购买次数/销量)
    val priors_feature = priors.selectExpr("product_id", "cast(reordered as int)")
      .groupBy("product_id")
      .agg(
        count("product_id").as("product_cnt"),
        sum("reordered").as("reorder_sum"),
        avg("reordered").as("reordered_rate")
      )

    // 用户特征
    //    1. 每个用户购买订单的平均间隔
    //    2. 每个用户的总订单数
    val user_period_orders = orders.selectExpr("user_id", "order_id", "if(days_since_prior_order='',0,days_since_prior_order) as dspo")
      .drop("days_since_prior_order")
      .selectExpr("user_id", "order_id", "cast(dspo as int)")
      .groupBy("user_id")
      .agg(
        avg("dspo").as("period"),
        count("order_id").as("order_cnt")
      )
    //    3. 每个用户购买的product商品去重后的集合数据
    import priors.sparkSession.implicits._
    val user_product_set = orders.join(priors, "order_id")
      .select("user_id", "product_id")
      .rdd.map(x => (x(0).toString, x(1).toString))
      .groupByKey().mapValues(_.toSet.mkString(","))
      .toDF("user_id", "product_set")
    //    4. 用户总商品数量以及去重后的商品数量
    val user_product_cnt = orders.join(priors, "order_id")
      .select("user_id", "product_id")
      .groupBy("user_id")
      .agg(
        count("product_id").as("user_product_cnt"),
        countDistinct("product_id").as("product_distinct_cnt")
      )
    //    5. 每个用户购买的平均每个订单商品数量
    val user_product_avg = priors.select("order_id", "product_id")
      .groupBy("order_id")
      .count()
      .withColumnRenamed("count", "product_cnt")
      .join(orders, "order_id")
      .groupBy("user_id")
      .avg("product_cnt")
      .withColumnRenamed("avg(product_cnt)", "product_avg")

    // 组合用户特征
    val user_feature = user_period_orders.join(user_product_set, "user_id")
      .join(user_product_cnt, "user_id")
      .join(user_product_avg, "user_id")
      .selectExpr("user_id",
        "cast(period as double)",
        "cast(order_cnt as int)",
        "cast(user_product_cnt as int)",
        "cast(product_distinct_cnt as int)",
        "cast(product_avg as double)",
        "product_set"
      )

    (priors_feature, user_feature)
  }
}
