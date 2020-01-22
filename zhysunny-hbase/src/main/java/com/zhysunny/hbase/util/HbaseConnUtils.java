package com.zhysunny.hbase.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import java.io.IOException;

/**
 * @author 章云
 * @date 2020/1/22 9:42
 */
public class HbaseConnUtils {

    public static Connection getConnection() throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "192.168.1.44");
        // 与hbase/conf/hbase-site.xml中hbase.zookeeper.property.clientPort配置的值相同
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        return ConnectionFactory.createConnection(conf);
    }

}
