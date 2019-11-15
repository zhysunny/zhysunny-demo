package com.zhysunny.util

object ParamUtils {
  val ZOOKEEPER_NODES = "master,slave1,slave2"
  val ZOOKEEPER_PORT = "2181"
  val ZOOKEEPER_CLIENT = ZOOKEEPER_NODES.split(",").map(_ + ":" + ZOOKEEPER_PORT).mkString(",")

  def main(args: Array[String]): Unit = {
    println(ZOOKEEPER_NODES)
    println(ZOOKEEPER_CLIENT)
  }
}
