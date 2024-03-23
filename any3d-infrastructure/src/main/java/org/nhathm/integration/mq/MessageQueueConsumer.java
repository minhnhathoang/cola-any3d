package org.nhathm.integration.mq;

import java.util.List;

public interface MessageQueueConsumer {

    void consume(List<Message> messages, Acknowledgement ack);
}