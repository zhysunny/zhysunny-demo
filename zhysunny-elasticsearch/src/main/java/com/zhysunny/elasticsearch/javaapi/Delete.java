package com.zhysunny.elasticsearch.javaapi;

import com.zhysunny.elasticsearch.client.EsUtils;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.byscroll.BulkByScrollResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryAction;

public class Delete {

    public static void main(String[] args) throws Exception {
        TransportClient client = EsUtils.getEsTransportClient("lv217.dct-znv.com-es", "10.45.154.217");
        deleteByQueryCallBack(client);
        client.close();
    }

    public static void deleteById(TransportClient client) {
        // 需要index，type，id
        DeleteResponse response = client.prepareDelete().setIndex("index").setType("type").setId("AWtFjwB3DUSuk2Wdkq71").get();
        System.out.println(response.getIndex());
        System.out.println(response.getType());
        System.out.println(response.getId());
        System.out.println(response.getVersion());
        System.out.println(response.status());
    }

    public static void deleteByQuery(TransportClient client) {
        BulkByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
        .filter(QueryBuilders.matchQuery("big_picture_uuid", "bigPictureUuid")) //查询条件
        .source("index") //index(索引名)
        .get(); //执行
        System.out.println(response.getDeleted());//删除文档的数量
        System.out.println(response.getStatus());
    }

    public static void deleteByQueryCallBack(TransportClient client) {
        DeleteByQueryAction.INSTANCE.newRequestBuilder(client).filter(QueryBuilders.matchQuery("big_picture_uuid", "bigUuid")) //查询条件
        .source("index") //index(索引名)
        .execute(new ActionListener<BulkByScrollResponse>() { //回调监听
            @Override
            public void onResponse(BulkByScrollResponse response) {
                System.out.println(response.getDeleted());//删除文档的数量
                System.out.println(response.getStatus());
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });
    }

}
