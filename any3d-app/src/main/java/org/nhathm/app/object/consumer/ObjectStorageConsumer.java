package org.nhathm.app.object.consumer;

import lombok.extern.slf4j.Slf4j;
import org.nhathm.integration.mq.Acknowledgement;
import org.nhathm.integration.mq.Message;
import org.nhathm.integration.mq.MessageQueueConsumer;
import org.nhathm.integration.mq.MessageQueueListener;

import java.util.List;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Slf4j
@MessageQueueListener(topic = "minio-events")
public class ObjectStorageConsumer implements MessageQueueConsumer {

    @Override
    public void consume(List<Message> messages, Acknowledgement ack) {
        log.info("Consume {} messages: {}", messages.size(), messages);

        for (Message message : messages) {
            log.info("Consume message: {}", message);
        }

        ack.acknowledge();
    }
}
