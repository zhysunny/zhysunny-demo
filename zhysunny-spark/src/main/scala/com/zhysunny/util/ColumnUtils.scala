package com.zhysunny.util

import scala.collection.mutable.ListBuffer

object ColumnUtils {
  def getColumns(): ListBuffer[String] = {
    val list: ListBuffer[String] = ListBuffer[String]()
    list.append("CAPTURE_TIME")
    list.append("MD_ID")
    list.append("MSISDN_1")
    list.append("MSIMSI_1")
    list.append("MSIMEI_1")
    list.append("LAC_1")
    list.append("CELLID_1")
    list.append("MSISDN_2")
    list.append("CALLTYPE")
    list.append("MSC_ID")
    list.append("ATTACH_1")
    list.append("ATTACH_2")
    list.append("OPC")
    list.append("DPC")
    list.append("REASONTYPE")
    list.append("RESULTTYPE")
    list.append("MESSAGE")
    list.append("CITYCODE")
    list.append("LONGITUDE")
    list.append("LATITUDE")
    list.append("CI_NAME")
    list.append("OPERATOR_NET")
    list.append("DATA_SOURCE")
    list.append("PROTOCOL")
    list.append("LANGUAGES")
    list.append("CHINESE_LANGUAGES")
    list.append("CLUE_ID")
    list.append("CLUE_SRC_SYS")
    list.append("CLUE_DST_SYS")
    list.append("GEOHASH")
    list.append("TAI")
    list.append("TA_LIST")
    list.append("ECGI")
    list.append("MME_GROUP_ID")
    list.append("MME_CODE_ID")
    list.append("APN")
    list.append("MME_IP")
    list.append("ENODEB_IP")
    list.append("UE_IP")
    list.append("SRC_IPV6")
    list.append("PHONE_LAND")
    list.append("PHONE_LAND_TO")
    list.append("GEOHASH4")
    list.append("GEOHASH5")
    list.append("GEOHASH6")
    list
  }

  def main(args: Array[String]): Unit = {
    val list = getColumns()
    val str = list.map("MEM:" + _).mkString(",")
    println(str)
  }
}
