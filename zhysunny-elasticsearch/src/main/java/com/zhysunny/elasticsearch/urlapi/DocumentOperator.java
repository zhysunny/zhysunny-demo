package com.zhysunny.elasticsearch.urlapi;

import com.alibaba.fastjson.JSONObject;

public class DocumentOperator {
    public static void main(String[] args) {
        String esUrl = "http://192.168.1.31:9200/";
        // 添加数据index=customer，type=external，id=1
        // upsert操作，doc已存在就修改
//        JSONObject json = new JSONObject(true);
//        json.put("name", "es");
//        json.put("age", 22);
//        System.out.println(new HttpConnection(esUrl + "customer/external/1", "PUT").send(json));
        // 查询数据
//        System.out.println(new HttpConnection(esUrl + "customer/external/1?pretty", "GET").get());
        // 修改数据
//        JSONObject update = new JSONObject(true);
//        JSONObject data = new JSONObject(true);
//        data.put("name", "elasticsearch");
//        data.put("age", 25);
//        update.put("doc", data);
//        System.out.println(new HttpConnection(esUrl + "customer/external/1/_update?pretty", "POST").send(update));
        // 修改数据(失败400Bad Request)
//        JSONObject script = new JSONObject(true);
//        // ctx._source表示当前文档
//        json.put("script", "ctx._source.age += 5");
//        System.out.println(new HttpConnection(esUrl + "customer/external/1/_update?pretty", "POST").send(script));
        // 删除数据
//        System.out.println(new HttpConnection(esUrl + "customer/external/1?pretty", "DELETE").get());
        // 批处理添加数据(每个json后面换行符必须要)
//        String insert = "{\"index\":{\"_id\":\"1\"}}\n" +
//                "{\"name\": \"John Doe\" }\n" +
//                "{\"index\":{\"_id\":\"2\"}}\n" +
//                "{\"name\": \"Jane Doe\" }\n";
//        System.out.println(new HttpConnection(esUrl + "customer/external/_bulk?pretty", "POST").send(insert));
        // 批处理修改删除数据(每个json后面换行符必须要)
//        StringBuffer sb = new StringBuffer(128);
//        //修改
//        JSONObject updateIdJson = new JSONObject(true);
//        JSONObject updateId = new JSONObject(true);
//        updateId.put("_id", "1");
//        updateIdJson.put("update", updateId);
//        sb.append(updateIdJson.toString()).append('\n');
//        JSONObject updateDocJson = new JSONObject(true);
//        JSONObject updateDoc = new JSONObject(true);
//        updateDoc.put("name", "John Doe becomes Jane Doe");
//        updateDocJson.put("doc", updateDoc);
//        sb.append(updateDocJson.toString()).append('\n');
//        //删除
//        JSONObject deleteIdJson = new JSONObject(true);
//        JSONObject deleteId = new JSONObject(true);
//        deleteId.put("_id", "2");
//        deleteIdJson.put("delete", deleteId);
//        sb.append(deleteIdJson.toString()).append('\n');
//        System.out.println(new HttpConnection(esUrl + "customer/external/_bulk?pretty", "POST").send(sb.toString()));
    }
}
