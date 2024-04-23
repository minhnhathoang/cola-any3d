package org.nhathm.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Arrays;

/**
 * @author nhathm
 */
@Slf4j
public class KafkaCustomDeserializer implements Deserializer<MQMessage> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public MQMessage deserialize(String s, byte[] bytes) {
        // implement from Bytes for MQMessage
        MQMessage message = new MQMessage();
        try {
            System.out.println("Deserialize message" + Arrays.toString(bytes));
            message = objectMapper.readValue(bytes, MQMessage.class);
            return message;
        } catch (Exception e) {
            log.error("Error when deserializing message", e);
            return null;
        }
    }
}