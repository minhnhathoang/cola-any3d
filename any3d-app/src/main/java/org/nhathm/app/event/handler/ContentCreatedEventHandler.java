package org.nhathm.app.event.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.nhathm.domain.user.database.UserMapper;
import org.nhathm.domain.user.dataobject.UserDO;
import org.nhathm.domain.user.entity.User;
import org.nhathm.dto.domainevent.ContentCreatedEvent;
import org.nhathm.websocket.WebSocketService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class ContentCreatedEventHandler {

    private final WebSocketService wsService;

    private final UserMapper userMapper;

    @EventListener
    public void handle(ContentCreatedEvent event) {
        log.info("[Event Handler] {}", event.getClass().getSimpleName());
    }

    @EventListener
    public void notify(ContentCreatedEvent event) {
        UserDO user = userMapper.selectOwnerByProjectId(event.getProjectId());
        wsService.notifyToUser(user.getId(), event);
        log.info("[Event Notify] {}", event.getClass().getSimpleName());
    }
}
