package com.zhysunny.spark.dataframe.transform

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Select {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("spark基本操作之查询字段")
      .enableHiveSupport()
      .getOrCreate()

    val df = spark.sql("select order_id,user_id,order_dow from badou.orders")
    // 根据传入的String类型字段名，获取指定字段的值，以DataFrame类型返回
    df.select("user_id", "order_dow").show()
    // 还有一个重载的select方法，不是传入String类型参数，而是传入Column类型参数。
    // 可以实现select id, id+1 from test这种逻辑。
    df.select(df("user_id") + 1, df("order_dow") + 1).show()
    // 能得到Column类型的方法是apply以及col方法，一般用apply方法更简便
    df.select(col("user_id") * 0.1, df.apply("order_dow") * 0.2).show()


    // selectExpr：可以对指定字段进行特殊处理
    // 可以直接对指定字段调用UDF函数，或者指定别名等。传入String类型参数，得到DataFrame对象。

    df.selectExpr("user_id as id", "round(order_dow)").show()

    // 返回一个新的DataFrame对象，其中不包含去除的字段，一次只能去除一个字段。
    df.drop("order_dow").show()
  }
}
