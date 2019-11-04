package com.zhysunny.elasticsearch.javaapi;

import com.zhysunny.elasticsearch.client.EsUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import static org.elasticsearch.common.xcontent.XContentFactory.*;

public class Upsert {
    public static void main(String[] args) throws Exception {
        TransportClient client = EsUtils.getEsTransportClient("lv217.dct-znv.com-es", "10.45.154.217");
        upsert(client);
        client.close();
    }

    public static void upsert(TransportClient client) throws Exception {
        XContentBuilder xContentBuilder = jsonBuilder().startObject().field("gender", "man").endObject();
        IndexRequest indexRequest = new IndexRequest("index", "type", "456").source(xContentBuilder);
        UpdateRequest updateRequest = new UpdateRequest("index", "type", "456").doc(xContentBuilder).upsert(indexRequest); //如果不存在此文档 ，就增加 `indexRequest`
        client.update(updateRequest).get();
    }

}
