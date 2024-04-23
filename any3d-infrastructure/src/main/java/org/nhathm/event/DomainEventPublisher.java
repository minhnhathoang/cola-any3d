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

    public void publish(String topic, Object msg) {
        kafkaTemplate.send(topic, msg);
    }

    public void publishProjectEvent(Object event) {
        this.publish("project-events-topic", event);
    }

    public void publish(Object event) {
        this.publish("events-topic", event);
    }
}
