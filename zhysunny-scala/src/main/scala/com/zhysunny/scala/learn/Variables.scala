package com.zhysunny.scala.learn

object Variables {
  def main(args: Array[String]): Unit = {
    println("############变量的定义##############")
    // (1)var 定义变量可变，val定义变量不可变
    // (2)推荐使用val
    // (3)和java一样8种基本数据类型，分别是Byte，Short，Int，Long，Char，Float，Double，Boolean，无包装类
    var a = 1 // 变量
    val b = 2 // 常量
    var num: Int = 333 // 显式类型
    val str: String = "hello" // 显式类型
    println(a, b, num, str)
  }
}
