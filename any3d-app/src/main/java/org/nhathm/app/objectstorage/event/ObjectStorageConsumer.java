package org.nhathm.app.objectstorage.event;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.domain.DomainFactory;
import com.alibaba.cola.exception.BizException;
import io.minio.messages.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhathm.domain.content.entity.Content;
import org.nhathm.domain.content.gateway.ContentGateway;
import org.nhathm.domain.hologram.entity.Hologram;
import org.nhathm.domain.hologram.gateway.HologramGateway;
import org.nhathm.domain.objectstorage.MetadataKey;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import util.json.JsonUtils;

import java.io.IOException;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@Component
@CatchAndLog
public class ObjectStorageConsumer {

    private final ContentGateway contentGateway;

    private final HologramGateway hologramGateway;

    private final HologramUploadedEventHandler hologramUploadedEventHandler;

    @KafkaListener(topics = "minio-events")
    public void consume(String payload, ConsumerRecordMetadata metadata) throws IOException {
        log.info("[MinIO Notification] - consume message: {} | metadata: {}", payload, metadata);
        try {
            ObjectStorageEvent record = JsonUtils.entity(payload, ObjectStorageEvent.class);
            for (Event event : record.events()) {
                handleEvent(event);
            }
        } catch (Exception e) {
            log.error("Error when parsing message: {}", e.getMessage());
        }
    }

    private void handleEvent(Event event) throws Exception {
        log.info("Event type: {}", event.eventType());
        switch (event.eventType()) {
            case OBJECT_CREATED_PUT -> {
                hologramUploadedEventHandler.handleObjectCreatedPut(event);
            }
        }
    }

    @Transactional(rollbackFor = BizException.class, propagation = Propagation.REQUIRES_NEW)
    void handleObjectCreatedPut(Event event) throws Exception {
        Map<String, String> userMetadata = event.userMetadata();
        if (!userMetadata.containsKey(MetadataKey.X_AMZ_META_IS_PRESIGNED_URL)) {
            return;
        }
        String projectId = userMetadata.get(MetadataKey.X_AMZ_META_PROJECT_ID);
        String filename = event.objectName();

        Content content = DomainFactory.create(Content.class);
        String contentId = event.objectName();
        content.setId(contentId);
        content.setProjectId(projectId);
        contentGateway.addContent(content);

        log.info("Object created: {}", contentId);
        if (true) {
            throw new RuntimeException("Error");
        }

        Hologram hologram = DomainFactory.create(Hologram.class);
        hologram.setContentId(contentId);
        hologram.setFilename(filename);
        hologramGateway.addHologram(hologram);

//        log.info("Object created: {}", event.objectName());
        String userId = userMetadata.get(MetadataKey.X_AMZ_META_USER_ID);
    }
}
