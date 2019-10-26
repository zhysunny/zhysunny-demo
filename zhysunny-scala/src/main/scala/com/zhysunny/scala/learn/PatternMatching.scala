package com.zhysunny.scala.learn

object PatternMatching {
  def main(args: Array[String]): Unit = {
    // 模式匹配

    val xs = List(1, 2, 3)
    val ys = List(4, 5, 6)
    println((xs zip ys) map { case (x, y) => x * y })


    // “v42”被解释为匹配任何Int值的名称，并打印“42”。
    val v42 = 42
    Some(3) match {
      case Some(v42) => println("42")
      case _ => println("Not 42")
    }
    // 带反号的“`v42`”被解释为现有的val v42，而“Not 42”被打印出来。
    Some(3) match {
      case Some(`v42`) => println("42")
      case _ => println("Not 42")
    }
    // UppercaseVal被视为一个现有的val，而不是一个新的模式变量，因为它以大写字母开始。
    // 因此，将针对3检查UppercaseVal中包含的值，并打印“Not 42”
    val UppercaseVal = 42
    Some(3) match {
      case Some(UppercaseVal) => println("42")
      case _ => println("Not 42")
    }
  }
}
