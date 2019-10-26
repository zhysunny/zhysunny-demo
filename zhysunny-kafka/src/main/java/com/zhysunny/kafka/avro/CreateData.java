package com.zhysunny.kafka.avro;

import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import static com.zhysunny.kafka.avro.FileDataFilter.*;

/**
 * @author 章云
 * @date 2019/10/24 9:42
 */
public class CreateData {

    public static void main(String[] args) {
        File path = new File("E:\\data");
        // 历史表
        File file = new File(path, "guard.txt");
        List<JSONObject> datas = readLines(file);
        Map<String, JSONObject> map = filter(datas, "event_id");
        int count = 1000;
        while (true) {
            try (FileOutputStream fos = new FileOutputStream("E:\\data\\g.txt", true);) {
                for (Map.Entry<String, JSONObject> entry : map.entrySet()) {
                    if (count == 0) {
                        return;
                    }
                    try {
                        entry.getValue().put("event_id", UUID.randomUUID().toString());
                        fos.write((entry.getValue().toJSONString() + "\n").getBytes());
                        count--;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
