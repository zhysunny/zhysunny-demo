package com.zhysunny.kafka.main;

import com.zhysunny.kafka.constant.FinalConstants;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import java.util.*;

/**
 * 重置kafka offset
 * @author 章云
 * @date 2019/11/15 21:48
 */
public class ResetOffset {

    private KafkaConsumer<String, String> consumer;
    private static String topic;

    public ResetOffset() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.1.44:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("auto.offset.reset", "earliest");
        properties.put("enable.auto.commit", "true");
        properties.put("group.id", FinalConstants.DEFAULT_GROUP_ID);
        consumer = new KafkaConsumer<String, String>(properties);
        topic = FinalConstants.DEFAULT_TOPIC_NAME;
    }

    public void resetOffset() {
        int partition = 3;
        OffsetAndMetadata offsetAndMetadata = new OffsetAndMetadata(0, "reset");
        List<TopicPartition> list = new ArrayList<TopicPartition>(partition);
        Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<TopicPartition, OffsetAndMetadata>(partition);
        TopicPartition tp = null;
        for (int i = 0; i < partition; i++) {
            tp = new TopicPartition(topic, i);
            list.add(tp);
            offsets.put(tp, offsetAndMetadata);
        }
        consumer.assign(list);
        consumer.commitSync(offsets);
        consumer.close();
    }

    public static void main(String[] args) {
        ResetOffset resetOffset = new ResetOffset();
        resetOffset.resetOffset();
    }
}
