package org.nhathm.app.project.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author nhathm
 */
@Component
public class ProjectConsumer {

    @KafkaListener(topics = "project-events-topic")
    public void consumeProjectEvent(Object msg) {
        System.out.println("Consuming project event: " + msg);
    }
}
