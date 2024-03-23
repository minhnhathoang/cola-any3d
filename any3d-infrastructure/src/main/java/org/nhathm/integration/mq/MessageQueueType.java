package org.nhathm.integration.mq;

public enum MessageQueueType {

    KAFKA,
    RABBITMQ;

    public static MessageQueueType parse(String type) {
        for (MessageQueueType messageQueueType : MessageQueueType.values()) {
            if (messageQueueType.name().equalsIgnoreCase(type)) {
                return messageQueueType;
            }
        }
        return null;
    }
}
