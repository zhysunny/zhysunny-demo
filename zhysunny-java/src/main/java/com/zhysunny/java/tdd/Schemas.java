package com.zhysunny.java.tdd;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 章云
 * @date 2019/12/2 15:15
 */
public class Schemas {

    Map<String, String> schema = new HashMap<>();

    public Schemas(String schemaConfig) {
        read(schemaConfig);
    }

    private void read(String schemaConfig) {
        Arrays.asList(schemaConfig.split(","))
        .forEach(split -> {
            String[] nameValue = split.split(":");
            schema.put(nameValue[0], nameValue[1]);
        });
    }

    public Object getValue(String name, String value) {
        String type = schema.get(name);
        switch (type) {
            case "bool":
                return "true".equalsIgnoreCase(value);
            case "int":
                return "".equals(value) || value == null ? 0 : Integer.parseInt(value);
            case "string":
                return "".equals(value) || value == null ? "" : value;
            default:
                return value;
        }
    }

}
