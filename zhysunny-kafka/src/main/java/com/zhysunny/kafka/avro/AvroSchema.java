package com.zhysunny.kafka.avro;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericContainer;

/**
 * 创建ZnvSchema类
 */
public enum AvroSchema {
    Null("null"), Bytes("bytes"), CharSequence("string"), Boolean("boolean"), Integer("int"), Long("long"), Float("float"), Double(
    "double"), Map(null);

    private Schema schema;

    AvroSchema(String type) {
        if (type == null) {
            schema = new Schema.Parser().parse(
            "{\"type\":\"map\", \"values\": [\"null\", \"int\", \"long\", \"float\", \"double\", \"string\", \"boolean\", \"bytes\"]}");
        } else {
            schema = new Schema.Parser().parse(String.format("{\"type\":\"%s\"}", type));
        }
    }

    public Schema schema() {
        return schema;
    }

    public static Schema getSchema(Object object) {
        if (object == null) {
            return Null.schema;
        }

        String clsName = object.getClass().getSimpleName();
        for (AvroSchema schema : values()) {
            if (clsName == schema.name()) {
                return schema.schema;
            }
        }

        if (object instanceof byte[]) {
            return Bytes.schema;
        } else if (object instanceof CharSequence) {
            return CharSequence.schema;
        } else if (object instanceof GenericContainer) {
            return ((GenericContainer)object).getSchema();
        } else if (object instanceof JSONObject) {
            return Map.schema;
        } else {
            String errMsg = String
            .format("Unsupported type: %s, Supported type are: %s", object.getClass().getName(), JSON.toJSONString(AvroSchema.values()));
            throw new IllegalArgumentException(errMsg);
        }

    }
}
