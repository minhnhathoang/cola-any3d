package org.nhathm.app.object.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Slf4j
@Component
public class ObjectStorageConsumer {

    @KafkaListener(topics = "minio-events")
    public void consume(Object messages) {
        log.info("Consumed messages: {}", messages);
    }
}
