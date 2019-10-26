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
        properties.put("bootstrap.servers", "10.45.154.216:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "com.zhysunny.tool.kafka.avro.KafkaAvroDeserializer");
        properties.put("auto.offset.reset", "earliest");
        properties.put("enable.auto.commit", "false");
        properties.put("group.id", FinalConstants.DEFAULT_GROUP_ID);
        consumer = new KafkaConsumer<>(properties);
        topic = "fss-history-n-project-v1-2-production";
    }

    public void start() {
        List<String> topics = new ArrayList<String>();
        topics.add(topic);
        consumer.subscribe(topics);
        List<JSONObject> datas = new ArrayList<>();
        while (true) {
            // 读取数据，读取超时时间为100ms
            ConsumerRecords<String, JSONObject> records = consumer.poll(60000);
            records.forEach(record -> datas.add(record.value()));
            // 告警表数据
            List<JSONObject> alarmDatas = datas.stream()
            .filter(json -> json.get("alarm_type") != null && json.getIntValue("alarm_type") == 3).collect(Collectors.toList());
            datas.removeAll(alarmDatas);
            write(alarmDatas, "E:\\data\\alarm.txt");
            // 门禁表
            List<JSONObject> guardDatas = datas.stream()
            .filter(json -> json.get("data_type") != null && "door".equals(json.getString("data_type"))).collect(Collectors.toList());
            datas.removeAll(guardDatas);
            write(guardDatas, "E:\\data\\guard.txt");
            // 更新数据
            List<JSONObject> updateDatas = datas.stream()
            .filter(json -> json.getString("updatedmessage") != null && json.getString("updatedmessage").contains("&")).collect(toList());
            datas.removeAll(updateDatas);
            write(updateDatas, "E:\\data\\update.txt");
            // 剩下的是新增历史表
            write(datas, "E:\\data\\history.txt");
        }
    }

    public static void main(String[] args) {
        KafkaConsumerConsole consumer = new KafkaConsumerConsole();
        consumer.start();
    }

    public static void write(List<JSONObject> datas, String fileName) {
        if (datas.size() == 0) {
            return;
        }
        datas.stream().forEach(System.out::println);
        try (FileOutputStream fos = new FileOutputStream(fileName, true);) {
            datas.forEach(json -> {
                try {
                    fos.write((json.toJSONString() + "\n").getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
