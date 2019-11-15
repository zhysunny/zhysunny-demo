package com.zhysunny.scala

import scala.util.Random

object Demo {
  def main(args: Array[String]): Unit = {
    val random = new Random()
    val str = (random.nextDouble()*100000).toInt
    println(str)
  }
}
