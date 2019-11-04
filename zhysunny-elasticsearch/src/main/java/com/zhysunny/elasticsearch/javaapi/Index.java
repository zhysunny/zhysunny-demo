package com.zhysunny.elasticsearch.javaapi;

import com.zhysunny.elasticsearch.client.EsUtils;
import org.elasticsearch.action.bulk.*;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static org.elasticsearch.common.xcontent.XContentFactory.*;

public class Index {
    public static void main(String[] args) throws Exception {
        TransportClient client = EsUtils.getEsTransportClient("lv217.dct-znv.com-es", "10.45.154.217");
        bulkRequest(client);
        client.close();
    }

    public static void index(TransportClient client) {
        Map<String, String> json = new HashMap<>();
        json.put("big_picture_uuid", "bigPictureUuid");
        IndexResponse response = client.prepareIndex("index", "type")
                .setSource(json)
                .get();
        System.out.println(response.getIndex());
        System.out.println(response.getType());
        System.out.println(response.getId());
        System.out.println(response.getVersion());
        System.out.println(response.status());
    }

    public static void bulkRequest(TransportClient client) throws IOException {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        bulkRequest.add(client.prepareIndex("index", "type", String.valueOf("123")).setSource(jsonBuilder()
                        .startObject()
                        .field("img_url", "imgUrl")
                        .field("big_picture_uuid", "bigUuid")
                        .endObject()
                )
        );
        bulkRequest.add(client.prepareIndex("index", "type", String.valueOf("456")).setSource(jsonBuilder()
                        .startObject()
                        .field("img_url", "imgUrl")
                        .field("big_picture_uuid", "bigUuid")
                        .endObject()
                )
        );
        BulkResponse bulkResponse = bulkRequest.get();
        if (bulkResponse.hasFailures()) {
            System.out.println(bulkRequest.toString());
        }
    }

    public static void bulkProcessor(TransportClient client) throws IOException, InterruptedException {
        BulkProcessor bulkProcessor = BulkProcessor.builder(client, //增加elasticsearch客户端
                new BulkProcessor.Listener() {
                    @Override
                    public void beforeBulk(long executionId, BulkRequest request) {
                        //调用bulk之前执行 ，例如你可以通过request.numberOfActions()方法知道numberOfActions
                        System.out.println("调用bulk之前");
                    }

                    @Override
                    public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
                        //调用bulk之后执行 ，例如你可以通过request.hasFailures()方法知道是否执行失败
                        System.out.println("调用bulk之后");
                    }

                    @Override
                    public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
                        //调用失败抛 Throwable
                        System.out.println("调用bulk失败");
                    }
                })
                .setBulkActions(10000) //每次10000请求
                .setBulkSize(new ByteSizeValue(5, ByteSizeUnit.MB)) //拆成5mb一块
                .setFlushInterval(TimeValue.timeValueSeconds(5)) //无论请求数量多少，每5秒钟请求一次。
                .setConcurrentRequests(1) //设置并发请求的数量。值为0意味着只允许执行一个请求。值为1意味着允许1并发请求。
                .setBackoffPolicy(BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))//设置自定义重复请求机制，最开始等待100毫秒，之后成倍更加，重试3次，当一次或多次重复请求失败后因为计算资源不够抛出 EsRejectedExecu tionException 异常，可以通过BackoffPolicy.noBackoff() 方法关闭重试机制
                .build();
//        BulkProcessor 默认设置
//        bulkActions 1000
//        bulkSize 5mb
//        不设置flushInterval
//        concurrentRequests 为 1 ，异步执行
//        backoffPolicy 重试 8次，等待50毫秒
        Map<String, String> json = new HashMap<>();
        json.put("bulkProcessor", "bulkProcessor");
        bulkProcessor.add(new IndexRequest("twitter", "tweet", "1").source(json));
        bulkProcessor.add(new DeleteRequest("twitter", "tweet", "2"));
        bulkProcessor.close();
//        bulkProcessor.awaitClose(10, TimeUnit.MINUTES);
    }
}
