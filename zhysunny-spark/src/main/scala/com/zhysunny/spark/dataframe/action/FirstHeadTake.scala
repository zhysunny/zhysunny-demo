package com.zhysunny.spark.dataframe.action

import org.apache.spark.sql.SparkSession

object FirstHeadTake {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("spark基本操作之first head take方法")
      .enableHiveSupport()
      .getOrCreate()

    val df = spark.sql("select order_id,user_id,order_dow from badou.orders where user_id='1'")

    //    以Row或者Array[Row]的形式返回一行或多行数据。first和head功能相同。
    //    take和takeAsList方法会将获得到的数据返回到Driver端，所以，使用这两个方法时需要注意数据量，
    //    以免Driver发生OutOfMemoryError

    // 获取第一行记录
    df.first()
    // 获取第一行记录
    df.head()
    // 获取前n行记录
    df.head(10)
    // 获取前n行记录
    df.take(10)
    // 获取前n行数据，并以List的形式展现
    df.takeAsList(10)
    //    scala> df.first()
    //    res3: org.apache.spark.sql.Row = [2539329,1,2]
    //
    //    scala> df.head()
    //    res4: org.apache.spark.sql.Row = [2539329,1,2]
    //
    //    scala> df.head(10)
    //    res5: Array[org.apache.spark.sql.Row] = Array([2539329,1,2], [2398795,1,3], [473747,1,3], [2254736,1,4], [431534,1,4], [3367565,1,2], [550135,1,1], [3108588,1,1], [2295261,1,1], [2550362,1,4])
    //
    //    scala> df.take(10)
    //    res6: Array[org.apache.spark.sql.Row] = Array([2539329,1,2], [2398795,1,3], [473747,1,3], [2254736,1,4], [431534,1,4], [3367565,1,2], [550135,1,1], [3108588,1,1], [2295261,1,1], [2550362,1,4])
    //
    //    scala> df.takeAsList(10)
    //    res7: java.util.List[org.apache.spark.sql.Row] = [[2539329,1,2], [2398795,1,3], [473747,1,3], [2254736,1,4], [431534,1,4], [3367565,1,2], [550135,1,1], [3108588,1,1], [2295261,1,1], [2550362,1,4]]
  }
}
