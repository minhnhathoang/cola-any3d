package org.nhathm.event;

import lombok.RequiredArgsConstructor;
import org.nhathm.dto.domainevent.BaseEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publish(BaseEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

    public void publish(Object event) {
        applicationEventPublisher.publishEvent(event);
    }
}
