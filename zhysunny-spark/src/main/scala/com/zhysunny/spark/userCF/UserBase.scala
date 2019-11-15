package com.zhysunny.spark.userCF

import breeze.numerics.{pow, sqrt}
import org.apache.spark.sql.SparkSession

object UserBase {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("userCF")
      .enableHiveSupport()
      .getOrCreate()
    // 获取表数据
    val df = spark.sql("select * from badou.udata")
    // 求用户相似度cos = a*b/(|a|*|b|)
    // DataFrame转rdd变成行记录
    // 循环每行记录把user_id和rating组成元组
    // groupByKey以user_id作为key，rating作为集合
    // 循环每个元组的value值，对value值得raing集合做平方和再开根号
    import spark.implicits._
    var userScoreSum = df.rdd.map(x => (x(0).toString, x(2).toString))
      .groupByKey()
      .mapValues(x => sqrt(x.toArray.map(rating => pow(rating.toDouble, 2)).sum))
      .toDF("user_id", "rating_sqrt_sum")

    // 复制一张表
    val df_v = df.selectExpr("user_id as user_v", "item_id", "rating as rating_v")
    // join前一张表中的item_id有重复值
    // join后相当于把item_id外的字段看成一个字段，这个字段做了笛卡尔积
    // 问题：在相似用户时，相同用户不做相似用户，两用户相似做组合不做排列，所以笛卡尔积有重复的值
    // 问题解决，做过滤，只有user_id<user_v的数据
    val df_join = df.join(df_v, "item_id")
      .filter("cast(user_id as long) <> cast(user_v as long)")
    // 如果是item_id和item_v做join，这时DataFrame中有item_id和item_v两个字段
    //     val df_join = df.join(df_v, df("item_id") === df_v("item_v"))
    // 测试笛卡尔积
    //    val tmp = df.select("user_id").distinct().filter("cast(user_id as long) <=5")
    //    tmp.join(tmp.selectExpr("user_id as user_v")).filter("user_id < user_v")
    import org.apache.spark.sql.functions._
    val product_udf = udf((s1: Int, s2: Int) => s1.toDouble * s2.toDouble)
    // 计算a*b
    var df_dot = df_join.withColumn("rating_product", product_udf(col("rating"), col("rating_v")))
      .select("user_id", "user_v", "rating_product")
      .groupBy("user_id", "user_v")
      .agg("rating_product" -> "sum")
      .withColumnRenamed("sum(rating_product)", "rating_product_sum")
    // 计算cos = a*b/(|a|*|b|)
    // 此时所有相似用户的分数就出来了
    val df_cos = df_dot.join(userScoreSum, "user_id")
      .join(userScoreSum.selectExpr("rating_sqrt_sum as rating_sqrt_sum_v", "user_id as user_v"), "user_v")
      .selectExpr("user_id", "user_v", "rating_product_sum/(rating_sqrt_sum*rating_sqrt_sum_v) as cos")

    // 按照分数排序，找出前n个相似用户
    // 先列转行排序找出前10
    // 再行转列获得前10相似用户
    var df_topN = df_cos.rdd.map(x => (x(0).toString, (x(1).toString, x(2).toString)))
      .groupByKey().mapValues(x => x.toArray.sortBy(_._2).reverse.slice(0, 10))
      .flatMapValues(x => x)
      .toDF("user_id", "user_v_and_cos")
      .selectExpr("user_id", "user_v_and_cos._1 as user_v", "user_v_and_cos._2 as cos")

    // 获取用户物品集合
    var df_user_item = df.rdd.map(x => (x(0).toString, x(1).toString + '_' + x(2).toString))
      .groupByKey().mapValues(_.toArray).toDF("user_id", "item_rating")

    // udf表示item_v集合中把item集合过滤掉
    var item_udf = udf((item: Seq[String], item_v: Seq[String]) => {
      var fMap = item.map(x => {
        var item_rating = x.split("_");
        (item_rating(0), item_rating(1))
      }).toMap;
      item_v.filter(x => {
        var item_rating = x.split("_");
        fMap.getOrElse(item_rating(0), -1) == -1
      })
    })

    // 余弦相似度乘以打分
    var rating_cos_udf = udf((cos: Double, items: Seq[String]) => {
      items.map(x => {
        var item_rating = x.split('_');
        item_rating(0) + '_' + item_rating(1).toDouble * cos
      })
    })
    // 结果和原udata表join两次
    var df_item_rating = df_topN.join(df_user_item, "user_id")
      .join(df_user_item.selectExpr("user_id as user_v", "item_rating as item_rating_v"), "user_v")
      .withColumn("item_rating_filter", item_udf(col("item_rating"), col("item_rating_v")))
      .select("user_id", "cos", "item_rating_filter")
      .withColumn("item_rating_filter", rating_cos_udf(col("cos"), col("item_rating_filter")))
      .select("user_id", "item_rating_filter")

    var df_items = df_item_rating.select(df_item_rating("user_id"), explode(df_item_rating("item_rating_filter")))
      .toDF("user_id", "item_product")
      .selectExpr("user_id", "split(item_product,'_')[0] as item_id", "cast(split(item_product,'_')[1] as double) score")

    // 取top10
    df_items.rdd.map(x => (x(0).toString, (x(1).toString, x(2).toString)))
      .groupByKey().mapValues(x => x.toArray.sortBy(_._2).reverse.slice(0, 10))
      .flatMapValues(x => x)
      .toDF("user_id", "item_score")
      .selectExpr("user_id", "item_score._1 as item_id", "item_score._2 as score")
  }
}
