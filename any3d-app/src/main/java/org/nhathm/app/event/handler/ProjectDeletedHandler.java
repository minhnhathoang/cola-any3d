package org.nhathm.app.event.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.nhathm.dto.domainevent.ProjectDeletedEvent;
import org.nhathm.websocket.WebSocketService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Log4j2
public class ProjectDeletedHandler {

    private final WebSocketService wsService;

    @EventListener
    public void notify(ProjectDeletedEvent event) {
        wsService.notifyToUser(event.getDeletedBy(), event);
        log.info("[Event Notify] {}, message: {}", event.getClass().getSimpleName(), event);
    }
}
