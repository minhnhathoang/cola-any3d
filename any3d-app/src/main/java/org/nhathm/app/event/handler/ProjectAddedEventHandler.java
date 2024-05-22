package org.nhathm.app.event.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.nhathm.domain.project.database.ProjectMapper;
import org.nhathm.domain.project.dataobject.ProjectDO;
import org.nhathm.domain.user.database.UserMapper;
import org.nhathm.dto.domainevent.ProjectAddedEvent;
import org.nhathm.websocket.WebSocketService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Log4j2
public class ProjectAddedEventHandler {

    private final ProjectMapper projectMapper;

    private final WebSocketService wsService;

    @EventListener
    public void notify(ProjectAddedEvent event) {
        ProjectDO projectDO = projectMapper.getById(event.getProjectId());
        event.setData(projectDO);
        wsService.notifyToUser(projectDO.getOwnerId(), event);
        log.info("[Event Notify] {}, message: {}", event.getClass().getSimpleName(), projectDO);
    }
}