package com.zhysunny.hbase;

import com.zhysunny.hbase.util.HbaseConnUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import java.io.IOException;
import static org.apache.hadoop.hbase.util.Bytes.*;

/**
 * @author 章云
 * @date 2020/1/21 10:39
 */
public class Query {

    public static void main(String[] args) throws IOException {
        String tableName = "ZHYSUNNY:TEST";
        Connection conn = HbaseConnUtils.getConnection();
        Scan scan = new Scan();
        Table table = conn.getTable(TableName.valueOf(tableName));

        // 获取单个数据
        Get get = new Get(toBytes("person1"));
        // 只获取一个字段
        get.addColumn(toBytes("ATTR"), toBytes("AGE"));
        Result result = table.get(get);
        print(result);

        // 按字段查询数据
        SingleColumnValueFilter filter = new SingleColumnValueFilter(toBytes("ATTR"),toBytes("AGE"), CompareFilter.CompareOp.EQUAL, toBytes("11"));
        scan.setFilter(filter);
        ResultScanner rs = table.getScanner(scan);
        for (Result r : rs) {
            print(r);
        }


    }

    private static void print(Result result) {
        String rowkey = Bytes.toString(result.getRow());
        System.out.println("======================");
        System.out.println(rowkey);
        for (Cell cell : result.rawCells()) {
            String col = Bytes.toString(CellUtil.cloneQualifier(cell));
            String fam = Bytes.toString(CellUtil.cloneFamily(cell));
            byte[] value = CellUtil.cloneValue(cell);
            //原表中的rowkey
            System.out.println("col = " + col + " fam = " + fam + " value = " + Bytes.toString(value));
        }
    }

}
