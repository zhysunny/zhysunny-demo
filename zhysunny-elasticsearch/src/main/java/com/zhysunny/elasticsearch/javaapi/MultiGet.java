package com.zhysunny.elasticsearch.javaapi;

import com.zhysunny.elasticsearch.client.EsUtils;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.client.transport.TransportClient;

public class MultiGet {
    public static void main(String[] args) throws Exception {
        TransportClient client = EsUtils.getEsTransportClient("lv217.dct-znv.com-es", "10.45.154.217");
        multiGet(client);
        client.close();
    }

    public static void multiGet(TransportClient client) throws Exception {
        MultiGetResponse multiGetItemResponses = client.prepareMultiGet()
                .add("index", "type", "1") //一个id的方式
                .add("index", "index", "AWtFnboEAsp5d_URy2Ha") //多个id的方式
                .add("index2", "type3", "3333") //可以从另外一个索引获取
                .get();
        for (MultiGetItemResponse itemResponse : multiGetItemResponses) { //迭代返回值
            GetResponse response = itemResponse.getResponse();
            if (response.isExists()) { //判断是否存在
                String json = response.getSourceAsString(); //_source 字段
                System.out.println(json);
            }
        }
    }
}
