package org.nhathm.integration.mq;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;

import java.util.Map;

@RequiredArgsConstructor
public class MessageQueueHelper {

    private static final String MQ_TYPE_NOT_FOUND = "Message queue type named '{}' not found";
    private final ApplicationContext applicationContext;
    private final String primary;

    public MessageQueueProvider getBean() {
        return getBean(primary);
    }

    public MessageQueueProvider getBean(String messageQueueType) {
        Map<String, MessageQueueProvider> messageQueueProviders =
                applicationContext.getBeansOfType(MessageQueueProvider.class);
        return messageQueueProviders.values().stream()
                .filter(predicate -> predicate.messageQueueType().equalsIgnoreCase(messageQueueType))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(MQ_TYPE_NOT_FOUND));
    }
}
