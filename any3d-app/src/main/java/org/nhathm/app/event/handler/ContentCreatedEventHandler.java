package org.nhathm.app.event.handler;

import lombok.extern.log4j.Log4j2;
import org.nhathm.dto.domainevent.ContentCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ContentCreatedEventHandler {

    @EventListener
    public void handle(ContentCreatedEvent event) {
        log.info("[Event Handler] {}", event.getClass().getSimpleName());
    }

    @EventListener
    public void notify(ContentCreatedEvent event) {
        log.info("[Event Notify] {}", event.getClass().getSimpleName());
    }
}
