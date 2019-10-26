package com.zhysunny.kafka.main;

import com.zhysunny.kafka.constant.FinalConstants;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author 章云
 * @date 2019/10/21 16:20
 */
public class KafkaConsumerConsole {

    private KafkaConsumer<String, String> consumer;
    private String topic;

    public KafkaConsumerConsole() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.1.44:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("auto.offset.reset", "earliest");
        properties.put("enable.auto.commit", "true");
        properties.put("group.id", FinalConstants.DEFAULT_GROUP_ID);
        consumer = new KafkaConsumer<>(properties);
        topic = FinalConstants.DEFAULT_TOPIC_NAME;
    }

    public void start() {
        List<String> topics = new ArrayList<String>();
        topics.add(topic);
        consumer.subscribe(topics);
        while (true) {
            // 读取数据，读取超时时间为100ms
            ConsumerRecords<String, String> records = consumer.poll(60000);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.value());
            }
        }
    }

    public static void main(String[] args) {
        KafkaConsumerConsole consumer = new KafkaConsumerConsole();
        consumer.start();
    }

}
