package com.zhysunny.hbase;

import com.zhysunny.hbase.util.HbaseConnUtils;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import java.io.IOException;

/**
 * @author 章云
 * @date 2020/1/21 10:58
 */
public class Delete {

    public static void main(String[] args) throws IOException {
        String tableName = "ZHYSUNNY:TEST";

        Connection conn = HbaseConnUtils.getConnection();
        Table table = conn.getTable(TableName.valueOf(tableName));
        org.apache.hadoop.hbase.client.Delete delete = new org.apache.hadoop.hbase.client.Delete(Bytes.toBytes("person2"));


        table.delete(delete);
    }

}
