package org.nhathm.kafka;


import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

/**
 * @author nhathm
 */
public class MyKafkaDeserializer implements Deserializer<MQMessage> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public MQMessage deserialize(String s, byte[] bytes) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            MQMessage mqMessage = (MQMessage) ois.readObject();
            ois.close();
            bis.close();
            return mqMessage;
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing MQMessage from Kafka topic ", e);
        }
    }
}
