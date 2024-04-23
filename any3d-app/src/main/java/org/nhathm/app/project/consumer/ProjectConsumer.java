package org.nhathm.app.project.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhathm.app.project.assembler.ProjectAssembler;
import org.nhathm.domain.project.gateway.ProjectGateway;
import org.nhathm.dto.domainevent.BaseEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * @author nhathm
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class ProjectConsumer {

    private final SimpMessagingTemplate template;

    private final ProjectAssembler projectAssembler;

    private final ProjectGateway projectGateway;

    @KafkaListener(topics = "project-events-topic")
    public void consumeProjectEvent(BaseEvent event) {

        log.info("[Project Event] - consume message: {}", event);
//        switch (event.getType()) {
//            case ProjectCreatedEvent.class -> {
//                event
//            }
//        }
//
//        String projectId = msg.getProjectId();
//        ProjectCO projectCO = projectAssembler.toCO(projectGateway.getById(projectId));
//        template.convertAndSendToUser(projectCO.getOwner().getUsername(), "/queue/notify", "CREATE PROJECT SUCCESSFUL: " + projectCO.getName());
    }
}
