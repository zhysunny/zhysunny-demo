package com.zhysunny.kafka.main;

import com.zhysunny.kafka.constant.FinalConstants;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * kafka控制台生产
 * @author 章云
 * @date 2019/10/21 16:20
 */
public class KafkaProducerConsole {

    private KafkaProducer<String, String> producer;
    private String topic;

    public KafkaProducerConsole() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.1.44:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(properties);
        topic = FinalConstants.DEFAULT_TOPIC_NAME;
    }

    public void start() throws ExecutionException, InterruptedException {
        ProducerRecord<String, String> record = null;
        Scanner scan = new Scanner(System.in);
        System.out.println("开始输入消息：");
        String value = scan.next();
        while (!"exit".equalsIgnoreCase(value)) {
            record = new ProducerRecord<>(topic, value);
            producer.send(record).get();
            System.out.println("消息发送成功");
            value = scan.next();
        }
        producer.close();
        System.out.println("关闭生产者");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        KafkaProducerConsole producer = new KafkaProducerConsole();
        producer.start();
    }

}
