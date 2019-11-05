package com.zhysunny.kafka.avro;

import com.alibaba.fastjson.JSONObject;
import com.zhysunny.kafka.constant.FinalConstants;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;

/**
 * @author 章云
 * @date 2019/10/21 16:20
 */
public class KafkaConsumerConsole {

    private KafkaConsumer<String, JSONObject> consumer;
    private String topic;

    public KafkaConsumerConsole() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "10.45.157.112:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "com.zhysunny.kafka.avro.KafkaAvroDeserializer");
        properties.put("auto.offset.reset", "earliest");
        properties.put("enable.auto.commit", "false");
        properties.put("group.id", FinalConstants.DEFAULT_GROUP_ID);
        consumer = new KafkaConsumer<>(properties);
        topic = "fss-facecluster-src-production";
    }

    public void start() {
        List<String> topics = new ArrayList<String>();
        topics.add(topic);
        consumer.subscribe(topics);
        while (true) {
            // 读取数据，读取超时时间为100ms
            ConsumerRecords<String, JSONObject> records = consumer.poll(60000);
            records.forEach(record -> System.out.println(record.value()));
        }
    }

    public static void main(String[] args) {
        KafkaConsumerConsole consumer = new KafkaConsumerConsole();
        consumer.start();
    }

}
