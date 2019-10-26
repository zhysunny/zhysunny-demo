package com.zhysunny.kafka.avro;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import static com.zhysunny.kafka.avro.FileDataFilter.*;

/**
 * kafka控制台生产
 * @author 章云
 * @date 2019/10/21 16:20
 */
public class KafkaProducerConsole {

    private KafkaProducer<String, JSONObject> producer;
    private String topic;
    private List<JSONObject> alarm;
    private List<JSONObject> guard;
    private List<JSONObject> history;
    private List<JSONObject> update;
    private int dataNum = 1000;

    public KafkaProducerConsole() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "10.45.154.216:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "com.zhysunny.tool.kafka.avro.KafkaAvroSerializer");
        producer = new KafkaProducer<>(properties);
        topic = "fss-history-n-project-v1-2-production-test";
    }

    public void start() throws ExecutionException, InterruptedException {
        loadData();
        ProducerRecord<String, JSONObject> record;
        for (int i = 0; i < dataNum; i++) {
            // 数据只有1000条
            int index = i % 1000;
            if (i >= 1000) {
                alarm.get(index).put("uuid", UUID.randomUUID().toString());
                guard.get(index).put("event_id", UUID.randomUUID().toString());
                history.get(index).put("uuid", UUID.randomUUID().toString());
            }
            record = new ProducerRecord<>(topic, alarm.get(index));
            producer.send(record).get();
            record = new ProducerRecord<>(topic, guard.get(index));
            producer.send(record).get();
            record = new ProducerRecord<>(topic, history.get(index));
            producer.send(record).get();
        }
        producer.close();
        System.out.println("关闭生产者");
    }

    public void loadData() {
        File path = new File("E:\\data");
        File file = new File(path, "alarm.txt");
        alarm = readLines(file);
        file = new File(path, "guard.txt");
        guard = readLines(file);
        file = new File(path, "history.txt");
        history = readLines(file);
        file = new File(path, "update.txt");
        update = readLines(file);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        KafkaProducerConsole producer = new KafkaProducerConsole();
        producer.start();
    }

}
