package com.zhysunny.kafka.avro;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 章云
 * @date 2019/10/23 14:08
 */
public class FileDataFilter {

    public static void main(String[] args) {
        File path = new File("E:\\data");
        // 告警表
        File file = new File(path, "alarm.txt");
        List<JSONObject> datas = readLines(file);
        Map<String, JSONObject> map = filter(datas, "op_time", "uuid");
        output(map, file);
        // 门禁表
        file = new File(path, "guard.txt");
        datas = readLines(file);
        map = filter(datas, "event_id");
        output(map, file);
        // 历史表
        file = new File(path, "history.txt");
        datas = readLines(file);
        map = filter(datas, "enter_time", "uuid");
        output(map, file);
    }

    public static List<JSONObject> readLines(File file) {
        List<JSONObject> datas = new ArrayList<>();
        if (!file.exists()) {
            return datas;
        }
        try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {
            return stream.map(str -> {
                JSONObject json = JSONObject.parseObject(str);
                json.forEach((key, value) -> {
                    if (value instanceof BigDecimal) {
                        json.put(key, ((BigDecimal)value).doubleValue());
                    }
                });
                return json;
            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datas;
    }

    public static Map<String, JSONObject> filter(List<JSONObject> datas, String... columns) {
        Map<String, JSONObject> map = new LinkedHashMap<>();
        datas.forEach(json -> {
            String docid = "";
            for (String column : columns) {
                docid += json.getString(column);
            }
            map.put(docid, json);
        });
        return map;
    }

    private static void output(Map<String, JSONObject> map, File file) {
        System.out.println(map.size() + "=" + file.getAbsolutePath());
        try (FileOutputStream fos = new FileOutputStream(file);) {
            map.forEach((key, value) -> {
                try {
                    fos.write((value.toJSONString() + "\n").getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
