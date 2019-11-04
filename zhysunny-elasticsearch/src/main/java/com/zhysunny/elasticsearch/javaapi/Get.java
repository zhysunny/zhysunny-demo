package com.zhysunny.elasticsearch.javaapi;

import com.zhysunny.elasticsearch.client.EsUtils;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;

public class Get {
    public static void main(String[] args) throws Exception {
        TransportClient client = EsUtils.getEsTransportClient("lv217.dct-znv.com-es", "10.45.154.217");
        get(client);
        client.close();
    }

    public static void get(TransportClient client) {
        // 需要index，type，id
        GetResponse response = client.prepareGet().setIndex("history_fss_data_n_project_v1_2-29").setType("history_data").setId("2019-09-12 11:37:1958282cc9-19d4-4104-accb-e85f53814bb9").get();
        System.out.println(response.getIndex());
        System.out.println(response.getType());
        System.out.println(response.getId());
        System.out.println(response.getVersion());
        System.out.println(response.getSource());
//        System.out.println(response.getSource().get("fused_time"));
//        System.out.println(response.getSource().get("first_time"));
    }
}
