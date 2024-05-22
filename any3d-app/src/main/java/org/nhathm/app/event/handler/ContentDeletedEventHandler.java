package org.nhathm.app.event.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.nhathm.domain.content.gateway.ContentGateway;
import org.nhathm.domain.user.database.UserMapper;
import org.nhathm.domain.user.dataobject.UserDO;
import org.nhathm.domain.userprofile.domainservice.VuforiaApiService;
import org.nhathm.domain.vuforia.database.VuforiaKeyMapper;
import org.nhathm.domain.vuforia.entity.VuforiaKey;
import org.nhathm.dto.domainevent.ContentCreatedEvent;
import org.nhathm.dto.domainevent.ContentDeletedEvent;
import org.nhathm.websocket.WebSocketService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import util.json.JsonUtils;

@Log4j2
@Component
@RequiredArgsConstructor
public class ContentDeletedEventHandler {

    private final ContentGateway contentGateway;

    private final VuforiaKeyMapper vuforiaKeyMapper;

    private final VuforiaApiService vuforiaApiService;

    @EventListener
    public void handle(ContentDeletedEvent event) {
        log.info("[Event Handler] {}, contentId={}", event.getClass().getSimpleName(), event.getContentId());
        var content = contentGateway.getById(event.getContentId());
        var keyDO = vuforiaKeyMapper.selectByProjectId(content.getProjectId());
        if (keyDO != null) {
            VuforiaKey key = new VuforiaKey();
            BeanUtils.copyProperties(keyDO, key);
            var response = vuforiaApiService.deleteTarget(key, content.getImageTarget().getId());
            if (!response.isSuccess()) {
                log.error("Failed to delete target from Vuforia: {}", JsonUtils.toJson(response));
            }
        }
    }
}
