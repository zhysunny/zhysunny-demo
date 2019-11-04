package com.zhysunny.elasticsearch.urlapi;

import com.zhysunny.elasticsearch.client.HttpConnection;

public class IndexOperator {
    public static void main(String[] args) {
        String esUrl = "http://10.45.157.112:9200/";
        // 创建索引customer
        System.out.println(new HttpConnection(esUrl + "reid_fss_data_n_project_v1_2", "PUT").get());
        // 删除索引
//        System.out.println(new HttpConnection(esUrl + "customer", "DELETE").get());
//        System.out.println(new HttpConnection(esUrl + "zhy_test_data_object", "PUT").get());
    }
}
