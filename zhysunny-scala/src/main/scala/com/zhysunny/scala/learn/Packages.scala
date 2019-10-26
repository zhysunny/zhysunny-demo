package com.zhysunny.scala.learn

object Packages {
  def main(args: Array[String]): Unit = {
    // 包
    import scala.collection._
    import scala.collection.SortedMap
    import scala.collection.{SortedMap, Seq}
    import scala.collection.{SortedMap => sm}
    import java.util.{Date => _, _}
  }
}
