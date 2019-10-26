package com.zhysunny.scala

import scala.collection.mutable.ArrayBuffer

object Demo {
  def init():String = {
    println("huangbo 666")
    return "huangbo"
  }

  def main(args: Array[String]): Unit = {
    lazy val name = init();
    println("666")
    println(name)
    println(name)
    println(name)
  }

}
