package org.nhathm.event;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author nhathm
 */
@Component
@RequiredArgsConstructor
public class DomainEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private void publish(String topic, Object msg) {
        kafkaTemplate.send(topic, msg);
    }

    public void publishUserEvent(Object msg) {
        publish("user-events-topic", msg);
    }

    public void publishProjectEvent(Object msg) {
        this.publish("project-events-topic", msg);
    }
}
