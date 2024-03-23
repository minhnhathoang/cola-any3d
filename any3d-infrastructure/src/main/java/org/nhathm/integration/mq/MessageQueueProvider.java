package org.nhathm.integration.mq;

public interface MessageQueueProvider {

    String messageQueueType();

    MessageSendResult syncSend(Message message);

    void asyncSend(Message message, MessageSendCallback messageCallback);
}
