package com.zhysunny.scala.learn

/**
  * lazy关键字
  */
object Lazy {

  def init(): String = {
    println("Lazy 666")
    return "Lazy"
  }

  def main(args: Array[String]): Unit = {
    // lazy懒加载，线程安全，只实例化一次，只在调用的时候实例化
    // 相当于java
    //    if (name == null) {
    //      init()
    //    }
    lazy val name = init()
    println("666")
    println(name)
    println(name)
    println(name)
  }
}
