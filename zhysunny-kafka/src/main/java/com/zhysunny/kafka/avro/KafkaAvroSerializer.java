package com.zhysunny.kafka.avro;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Kafka Avro 序列化
 * @author 章云
 * @date 2019/10/22 14:56
 */
public class KafkaAvroSerializer implements Serializer<Object> {

    private final EncoderFactory encoderFactory = EncoderFactory.get();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, Object record) {
        Schema schema = null;
        if (record == null) {
            return null;
        }
        ByteArrayOutputStream out = null;
        DatumWriter<Object> writer = null;
        try {
            schema = AvroSchema.Map.schema();
            out = new ByteArrayOutputStream();
            if (record instanceof byte[]) {
                out.write((byte[])record);
            } else {
                BinaryEncoder encoder = encoderFactory.directBinaryEncoder(out, null);
                Object value = record;
                if (value instanceof SpecificRecord) {
                    writer = new SpecificDatumWriter<Object>(schema);
                } else {
                    writer = new GenericDatumWriter<Object>(schema);
                }
                writer.write(value, encoder);
                encoder.flush();
            }
            byte[] bytes = out.toByteArray();
            return bytes;
        } catch (Exception e) {
            throw new SerializationException("Error serializing Avro message", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void close() {
    }

}
