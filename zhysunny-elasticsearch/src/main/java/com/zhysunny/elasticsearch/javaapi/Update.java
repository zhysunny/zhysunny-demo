package com.zhysunny.elasticsearch.javaapi;

import com.zhysunny.elasticsearch.client.EsUtils;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import static org.elasticsearch.common.xcontent.XContentFactory.*;

public class Update {
    public static void main(String[] args) throws Exception {
        TransportClient client = EsUtils.getEsTransportClient("lv217.dct-znv.com-es", "10.45.154.217");
        updateRequest(client);
        client.close();
    }

    public static void updateRequest(TransportClient client) throws Exception {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("fused_data_realtime_test");
        updateRequest.type("fused");
        updateRequest.id("d7ff84b4-c61a-4ea3-9b11-4db1994222d7");
        updateRequest.doc(jsonBuilder()
                .startObject()
                .field("fused_time", "2019-09-19T10:08:49.655+08:00")
                .field("first_time", "2019-09-19T10:08:46.117+08:00")
                .endObject());
        client.update(updateRequest).get();
    }

    public static void prepareUpdate(TransportClient client) throws Exception {
        client.prepareUpdate("index", "type", "AWtFnboEAsp5d_URy2Ha")
                .setDoc(jsonBuilder() //合并到现有文档
                        .startObject()
                        .field("gender", "woman")
                        .field("test",11)
                        .endObject())
                .get();
    }

}
