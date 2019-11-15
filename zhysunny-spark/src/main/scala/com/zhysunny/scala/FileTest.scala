package com.zhysunny.scala

import java.net.URI

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}

object FileTest {
  def main(args: Array[String]): Unit = {
    val dfs = "hdfs://master:9000/user/hive/external/jz_resource_mem"
    val conf = new Configuration()
    val fs = FileSystem.get(URI.create(dfs), conf)
    val iterator = fs.listFiles(new Path(dfs), true)
    while (iterator.hasNext) {
      val status = iterator.next()
      println(status.getPath)
      println(status.getLen())
    }

  }
}
