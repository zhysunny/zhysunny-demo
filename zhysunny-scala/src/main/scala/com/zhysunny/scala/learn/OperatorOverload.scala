package com.zhysunny.scala.learn

object OperatorOverload {
  def main(args: Array[String]): Unit = {
    // 操作符重载，既是操作符，也是方法
    println(1 + 2)
    println(1.+(2))
    println(1 to 10)
    println(1.to(10))
  }
}
