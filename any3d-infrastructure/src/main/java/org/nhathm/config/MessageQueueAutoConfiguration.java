package org.nhathm.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhathm.integration.mq.MessageQueueHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Slf4j
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
public class MessageQueueAutoConfiguration {

    public static final String AUTOWIRED_MESSAGE_QUEUE_HELPER = "Autowired MessageQueueHelper";
    private final ApplicationContext applicationContext;
    private final MessageQueueProperties messageQueueProperties;

    @Bean
    public MessageQueueHelper messageQueueHelper() {
        log.debug(AUTOWIRED_MESSAGE_QUEUE_HELPER);
        return new MessageQueueHelper(applicationContext, messageQueueProperties.getPrimary());
    }
}