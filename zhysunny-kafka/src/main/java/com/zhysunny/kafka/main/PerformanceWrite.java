package com.zhysunny.kafka.main;

import com.zhysunny.common.random.RandomUtils;
import com.zhysunny.kafka.constant.FinalConstants;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import java.util.*;

/**
 * 写kafka性能测试
 * @author 章云
 * @date 2019/10/17 13:51
 */
public class PerformanceWrite {

    public static Properties getProperties() {
        Properties prop = new Properties();
        prop.put("bootstrap.servers", "192.168.1.44:9092");
        prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return prop;
    }

    /**
     * 模拟数据
     * @param length          字符串长度
     * @param initialCapacity 数据量
     * @return
     */
    public static List<String> simulationData(int length, int initialCapacity) {
        List<String> datas = new ArrayList<>(initialCapacity);
        for (int i = 0; i < initialCapacity; i++) {
            datas.add(RandomUtils.allCode(length));
        }
        return datas;
    }

    public static void main(String[] args) {
        KafkaProducer producer = new KafkaProducer(getProperties());
        System.out.println("start send msg....");
        List<String> datas = simulationData(1024, 1000);
        long start = System.currentTimeMillis();
        for (int i = 0; i < FinalConstants.DEFAULT_DATA_NUM / datas.size(); i++) {
            datas.forEach(message -> {
                ProducerRecord<String, String> record = new ProducerRecord(FinalConstants.DEFAULT_TOPIC_NAME, message);
                producer.send(record, new ProducerCallback());
            });
            System.out.println("one cycle over!!!");
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println("end send msg....");
        producer.close();
    }

}

class ProducerCallback implements Callback {

    @Override
    public void onCompletion(RecordMetadata metadata, Exception e) {
        if (metadata == null) {
            // 如果有异常消息，打印显示
            if (e != null) {
                e.printStackTrace();
            }
        } else {
            System.out.println(metadata.topic());
            System.out.println(metadata.offset());
            System.out.println(metadata.partition());
            System.out.println("send ok");
        }
    }

}
