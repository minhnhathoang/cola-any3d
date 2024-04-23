package org.nhathm.app.objectstorage.event;

import com.alibaba.cola.domain.DomainFactory;
import io.minio.messages.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.nhathm.domain.content.entity.Content;
import org.nhathm.domain.content.gateway.ContentGateway;
import org.nhathm.domain.hologram.entity.Hologram;
import org.nhathm.domain.hologram.gateway.HologramGateway;
import org.nhathm.domain.objectstorage.MetadataKey;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Log4j2
@Component
@RequiredArgsConstructor
public class HologramUploadedEventHandler {

    private final ContentGateway contentGateway;

    private final HologramGateway hologramGateway;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void handleObjectCreatedPut(Event event) {
        Map<String, String> userMetadata = event.userMetadata();
        if (!userMetadata.containsKey(MetadataKey.X_AMZ_META_IS_PRESIGNED_URL)) {
            return;
        }

        String projectId = userMetadata.get(MetadataKey.X_AMZ_META_PROJECT_ID);
        String filename = userMetadata.get(MetadataKey.X_AMZ_META_FILE_NAME);

        Content content = DomainFactory.create(Content.class);
        String contentId = event.objectName();
        content.setId(contentId);
        content.setProjectId(projectId);
        contentGateway.addContent(content);
        log.info("Content created: {}", contentId);

        Hologram hologram = DomainFactory.create(Hologram.class);
        hologram.setContentId(contentId);
        hologram.setFilename(filename);
        hologramGateway.addHologram(hologram);
        log.info("Hologram uploaded: {}", contentId);
    }
}
