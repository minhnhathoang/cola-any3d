package org.nhathm.event;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DomainEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publish(String topic, Object msg) {
        kafkaTemplate.send(topic, msg);
    }
}
