package com.zhysunny.hbase;

import com.zhysunny.hbase.util.HbaseConnUtils;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * hbase新增数据
 * @author 章云
 * @date 2020/1/20 17:02
 */
public class Insert {

    public static void main(String[] args) throws IOException {
        String tableName = "ZHYSUNNY:TEST";
        Connection conn = HbaseConnUtils.getConnection();
        Table hTable = conn.getTable(TableName.valueOf(tableName));

        Put p = new Put(Bytes.toBytes("person1"));
        p.addColumn(Bytes.toBytes("ATTR"), Bytes.toBytes("AGE"), Bytes.toBytes("11"));
        p.addColumn(Bytes.toBytes("APPEARANCE"), Bytes.toBytes("HEIGHT"), Bytes.toBytes("160.00"));
        hTable.put(p);

        p = new Put(Bytes.toBytes("person2"));
        p.addColumn(Bytes.toBytes("ATTR"), "AGE".getBytes(Charset.forName("GBK")), Bytes.toBytes(0));
        p.addColumn(Bytes.toBytes("APPEARANCE"), Bytes.toBytes("HEIGHT"), Bytes.toBytes(0));
        hTable.put(p);

        hTable.close();
    }

}
