package com.zhysunny.scala.learn

object ControlConstructs {
  def main(args: Array[String]): Unit = {
    // 控制结构
    val a = 1
    val b = 2
    val num: Int = 333
    val str: String = "hello"
    println("############条件表达式##############")
    // ()表示无返回值
    val result = if (a > b) "a>b" else if (a < b) "a<b" else ()
    println(result)
    println("############for循环##############")
    //    循环1到10，包含1和10
    for (i <- 1 to 10) {
      print(i)
    }
    println()
    //    循环1到10，包含1不包含10
    for (i <- 1 until 10) {
      print(i)
    }
    println()
    //    循环字符串的字符
    for (i <- str) {
      print(i)
    }
    println()
    //    循环数组，注意数组获取方式由中括号变成小括号
    val arr = Array(1, 2, 3, 4, 5, 6, 7)
    for (i <- 0 until arr.length) {
      print(arr(i))
    }
    println()
    //    高级for循环，相当于java的嵌套循环，建议if前面不加分号
    for (i <- 1 to 3; j <- 1 to 3 if i != j) {
      print(i, j)
    }
    println()
    //    For循环推导式，如果循环体以yield开始，则循环会构建一个集合
    val v = for (i <- 1 to 10) yield i * 10
    print(v)
  }
}
