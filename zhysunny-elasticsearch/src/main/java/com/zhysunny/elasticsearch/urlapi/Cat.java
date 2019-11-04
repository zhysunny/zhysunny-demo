package com.zhysunny.elasticsearch.urlapi;

import com.zhysunny.elasticsearch.client.HttpConnection;

public class Cat {
    public static void main(String[] args) {
        String esUrl = "http://192.168.1.31:9200/";
        // 查看所有_cat api
//        System.out.println(new HttpConnection(esUrl + "_cat", "GET").get());
        // 查看集群健康状态
//        System.out.println(new HttpConnection(esUrl + "_cat/health", "GET").get());
        // 加上?v相当于添加一行表头
//        System.out.println(new HttpConnection(esUrl + "_cat/health?v", "GET").get());
        // format=json表示结果为json格式,pretty表示格式化json字符串
//        System.out.println(new HttpConnection(esUrl + "_cat/health?format=json&pretty", "GET").get());
        // 查看集群节点
//        System.out.println(new HttpConnection(esUrl + "_cat/nodes?v", "GET").get());
        // 列出所有索引
        System.out.println(new HttpConnection(esUrl + "_cat/indices?format=json&pretty", "GET").get());
    }
}
