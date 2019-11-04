package com.zhysunny.elasticsearch.urlapi;

import com.zhysunny.elasticsearch.client.HttpConnection;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchApi {
    public static void main(String[] args) {
        String esUrl = "http://10.45.154.217:9200/";
        //查询所有数据，按age降序排序，默认展示10个结果的json字符串
//        System.out.println(new HttpConnection(esUrl + "search_test/_search/?q=*&sort=age:desc&pretty", "GET").get());
        // json方式查询
        JSONObject search = new JSONObject(true);
        JSONObject query = new JSONObject(true);
        query.put("match_all", new JSONObject(true));
        search.put("query", query);
        List<JSONObject> sortList = new ArrayList<JSONObject>();
        JSONObject sort1 = new JSONObject(true);
        sort1.put("age", "desc");
        sortList.add(sort1);
        search.put("sort", sortList);
        System.out.println(new HttpConnection(esUrl + "person_list_data_n_project_v1_2/_search?pretty", "GET").send(search));
//        {
//            "query": { "match_all": {} }, //查询
//            "_source": ["name", "age"], //只返回name和age字段
//            "sort": [
//                { "age": "desc" }. //排序"sort": { "age": {"order":"desc"} }.
//            ]
//            "from": 10, //默认为0
//            "size": 10  //默认为10
//        }
    }
}
