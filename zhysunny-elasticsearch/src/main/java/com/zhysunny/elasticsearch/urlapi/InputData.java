package com.zhysunny.elasticsearch.urlapi;

import com.zhysunny.common.random.RandomUtils;
import com.zhysunny.elasticsearch.client.HttpConnection;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

public class InputData {

    public static void main(String[] args) {
        int count = 10;
        String index = "history_fss_reid_data_test";
        String type = "test";
        String esUrl = "http://10.45.157.112:9200/";
        String listIndexJson = new HttpConnection(esUrl + "_cat/indices?format=json&pretty", "GET").get();
        JSONArray jsonList = JSONArray.parseArray(listIndexJson);
        String indexName = null;
        boolean isExsistIndex = false;
        for (Object json : jsonList) {
            if (((JSONObject)json).containsKey("index")) {
                indexName = ((JSONObject)json).get("index").toString();
                if (index.equals(indexName)) {
                    isExsistIndex = true;
                }
            }
        }
        if (!isExsistIndex) {
            //如果索引不存在，创建索引
            System.out.println(new HttpConnection(esUrl + index, "PUT").get());
        }
        // 添加随机数据
        StringBuffer insert = new StringBuffer(100 * 64);
        JSONObject insertIdJson = null;
        JSONObject insertId = null;
        JSONObject insertDocJson = null;
        for (int i = 0; i < count; i++) {
            // 设置ID
            insertIdJson = new JSONObject(true);
            insertId = new JSONObject(true);
            insertId.put("_id", i);
            insertIdJson.put("index", insertId);
            insert.append(insertIdJson.toString()).append('\n');
            // 获得随机数
            String name = RandomUtils.smallCode(5) + " " + RandomUtils.smallCode(5);
            int age = Integer.parseInt(RandomUtils.numberCode(2));
            insertDocJson = new JSONObject(true);
            Set<String> set = new TreeSet<String>();
            set.add(RandomUtils.numberCode(1));
            set.add(RandomUtils.numberCode(1));
            set.add(RandomUtils.numberCode(1));
            set.add(RandomUtils.numberCode(1));
            set.add(RandomUtils.numberCode(1));
            insertDocJson.put("RespiratorColor", new JSONArray(new ArrayList<>(set)));
            insert.append(insertDocJson.toString()).append('\n');
        }
        System.out.println(new HttpConnection(esUrl + index + "/" + type + "/_bulk?pretty", "POST").send(insert.toString()));
    }

}
