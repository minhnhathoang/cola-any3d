package org.nhathm.integration.mq.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhathm.config.MessageQueueProperties;
import org.nhathm.integration.mq.MessageQueueConsumer;
import org.nhathm.integration.mq.MessageQueueProvider;
import org.nhathm.integration.mq.MessageQueueType;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import util.Conditions;
import util.StringUtils;

import java.util.List;
import java.util.function.Function;

@ConditionalOnProperty(
        prefix = MessageQueueProperties.PREFIX,
        name = Conditions.PRIMARY,
        havingValue = "Kafka"
)
@ConditionalOnExpression("${spring.kafka.enabled:true}")
@RequiredArgsConstructor
@Slf4j
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@Configuration(proxyBeanMethods = false)
public class KafkaMessageQueueConfig {
    private static final String AUTOWIRED_KAFKA_CONSUMER = "Autowired KafkaConsumer";

    private static final String AUTOWIRED_KAFKA_PROVIDER = "Autowired KafkaProvider";

    private final MessageQueueProperties messageQueueProperties;

    private final KafkaProperties kafkaProperties;

    @Bean
    public KafkaConsumer kafkaConsumer(ObjectProvider<List<MessageQueueConsumer>> messageListeners,
                                       ObjectProvider<ConsumerFactory<String, String>> consumerFactory) {
        log.info(AUTOWIRED_KAFKA_CONSUMER);
        Function<String, Boolean> matcher = type -> StringUtils.isBlank(type) && messageQueueProperties.getPrimary() != null ?
                MessageQueueType.KAFKA.name().equalsIgnoreCase(messageQueueProperties.getPrimary()) :
                MessageQueueType.KAFKA.name().equalsIgnoreCase(type);
        return new KafkaConsumer(kafkaProperties, messageListeners.getIfAvailable(),
                consumerFactory.getIfAvailable(), matcher);
    }

    @Bean
    public MessageQueueProvider messageQueueProvider(KafkaTemplate<String, String> kafkaTemplate) {
        log.info(AUTOWIRED_KAFKA_PROVIDER);
        return new KafkaProvider(kafkaTemplate);
    }
}
