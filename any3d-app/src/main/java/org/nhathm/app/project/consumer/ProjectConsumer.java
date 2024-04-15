package org.nhathm.app.project.consumer;

import lombok.extern.slf4j.Slf4j;
import org.nhathm.integration.mq.Acknowledgement;
import org.nhathm.integration.mq.Message;
import org.nhathm.integration.mq.MessageQueueConsumer;
import org.nhathm.integration.mq.MessageQueueListener;

import java.util.List;

/**
 * @author nhathm
 */
@Slf4j
@MessageQueueListener(topic = "project-events", group = "project-consumer")
public class ProjectConsumer implements MessageQueueConsumer {

    @Override
    public void consume(List<Message> messages, Acknowledgement ack) {
        for (Message message : messages) {
            log.info("Received message: {}", message);
        }
        ack.acknowledge();
    }
}
