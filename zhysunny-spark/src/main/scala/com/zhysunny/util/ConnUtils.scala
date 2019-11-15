package com.zhysunny.util

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.HBaseAdmin

object ConnUtils {
  def getHbaseConn(): HBaseAdmin = {
    val hconf = HBaseConfiguration.create()
    hconf.set("hbase.zookeeper.quorum", ParamUtils.ZOOKEEPER_NODES)
    hconf.set("hbase.zookeeper.property.clientPort", ParamUtils.ZOOKEEPER_PORT)
    val admin = new HBaseAdmin(hconf)
    admin
  }
}
