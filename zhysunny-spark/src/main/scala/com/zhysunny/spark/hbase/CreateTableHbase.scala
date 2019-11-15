package com.zhysunny.spark.hbase

import com.zhysunny.util.ConnUtils
import org.apache.hadoop.hbase.{HColumnDescriptor, HTableDescriptor, TableName}
import org.apache.hadoop.hbase.client.HTable

object CreateTableHbase {
  def main(args: Array[String]): Unit = {
    val tableName = "JZ_RESOURCE_MEM"
    val admin = ConnUtils.getHbaseConn()
    //    val htable = new HTable(admin.getConfiguration, tableName)
    if (admin.tableExists(tableName)) {
      println(tableName + "表已存在")
      admin.disableTable(tableName)
      admin.deleteTable(tableName)
    } else {
      val htable = new HTableDescriptor(TableName.valueOf(tableName))
      htable.addFamily(getHColumn("MEM"))
      admin.createTable(htable)
      println(tableName + "表创建成功")
    }
    admin.close()
  }

  /**
    * 增加列族
    *
    * @param column
    * @return
    */
  def getHColumn(column: String): HColumnDescriptor = {
    val hcolumn = new HColumnDescriptor(column)
    hcolumn.setBlocksize(2 << 20) //1M
    hcolumn.setBlockCacheEnabled(false)
    hcolumn.setMaxVersions(2)
    hcolumn
  }
}
