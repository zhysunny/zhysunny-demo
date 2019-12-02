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

    public Schemas(String schema) {
        read(schema);
    }

    private void read(String schemaConfig) {
        Arrays.asList(schemaConfig.split(","))
        .forEach(entry -> {
            String[] kv = entry.split(":");
            this.schema.put(kv[0], kv[1]);
        });
    }

    public Object getValue(String name, String value) {
        String type = this.schema.get(name);
        if ("bool".equalsIgnoreCase(type) || "boolean".equalsIgnoreCase(type)) {
            return "true".equalsIgnoreCase(value);
        } else if ("int".equalsIgnoreCase(type) || "integer".equalsIgnoreCase(type)) {
            return "".equals(value) || value == null ? 0 : Integer.parseInt(value);
        } else if ("string".equalsIgnoreCase(type)) {
            return value == null ? "" : value;
        } else {
            return value;
        }
    }

}
