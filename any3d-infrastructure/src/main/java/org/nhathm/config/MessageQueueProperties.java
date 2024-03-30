package org.nhathm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = MessageQueueProperties.PREFIX)
public class MessageQueueProperties {

    public static final String PREFIX = "spring.message-queue.dynamic";

    private boolean enabled;

    private String primary = "Kafka";
}
