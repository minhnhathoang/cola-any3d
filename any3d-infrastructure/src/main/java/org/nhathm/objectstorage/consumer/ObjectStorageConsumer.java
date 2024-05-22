package org.nhathm.objectstorage.consumer;

import io.minio.messages.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhathm.objectstorage.MetadataKey;
import org.nhathm.objectstorage.event.ObjectStorageEvent;
import org.nhathm.event.EventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.stereotype.Component;
import util.json.JsonUtils;

import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@Component
public class ObjectStorageConsumer {

    private final EventPublisher eventPublisher;

    @KafkaListener(topics = "minio-events")
    public void consume(String payload, ConsumerRecordMetadata metadata) {
        log.info("[MinIO Notification] - consume message: {} | metadata: {}", payload, metadata);
        try {
            ObjectStorageEvent record = JsonUtils.entity(payload, ObjectStorageEvent.class);
            for (Event event : record.events()) {
                handleEvent(event);
            }
        } catch (Exception e) {
            log.error("Error when parsing message", e);
        }
    }
    private void handleEvent(Event event) {
        log.info("Event type: {}", event.eventType());
        Map<String, String> userMetadata = event.userMetadata();
        if (!userMetadata.containsKey(MetadataKey.X_AMZ_META_IS_PRESIGNED_URL)) {
            return;
        }

        switch (event.eventType()) {
            case OBJECT_CREATED_PUT -> {
                eventPublisher.publish(event);
            }
        }
    }
}
