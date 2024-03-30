package org.nhathm.integration.mq.kafka;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.nhathm.integration.mq.Message;
import org.nhathm.integration.mq.MessageQueueConsumer;
import org.nhathm.integration.mq.MessageQueueListener;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.ConsumerFactory;
import util.Strings;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer implements InitializingBean, DisposableBean {

    public static final String INITIALIZING_KAFKA_CONSUMER = "Initializing KafkaConsumer";
    public static final String DESTROY_KAFKA_CONSUMER = "Destroy KafkaConsumer";
    public static final String CREATE_CONSUMER_FROM_CONSUMER_FACTORY_GROUP_TOPIC = "Create consumer from consumerFactory, group: {}, topic: {}";
    private static final String KAFKA_CONSUMER_PROCESSOR_CONSUME_ERROR = "KafkaConsumerProcessor consume error: {}";
    private final List<Consumer<String, String>> consumers = Lists.newArrayList();

    private final KafkaProperties kafkaProperties;

    private final List<MessageQueueConsumer> messageQueueConsumers;

    private final ConsumerFactory<String, String> consumerFactory;

    private final Function<String, Boolean> matcher;

    private final AtomicBoolean threadRunning = new AtomicBoolean(false);

    @Override
    public void afterPropertiesSet() {
        log.debug(INITIALIZING_KAFKA_CONSUMER);
        if (CollectionUtils.isEmpty(messageQueueConsumers)) {
            return;
        }
        for (MessageQueueConsumer messageQueueConsumer : messageQueueConsumers) {
            Consumer<String, String> consumer = initKafkaConsumer(messageQueueConsumer);
            if (consumer == null) {
                continue;
            }
            consumers.add(consumer);

            new Thread(() -> {
                while (threadRunning.get()) {
                    try {
                        ConsumerRecords<String, String> consumerRecords =
                                consumer.poll(kafkaProperties.getConsumer().getFetchMaxWait());
                        if (consumerRecords == null || consumerRecords.isEmpty()) {
                            continue;
                        }
                        int maxPollRecords = kafkaProperties.getConsumer().getMaxPollRecords();
                        Map<TopicPartition, OffsetAndMetadata> offsets = Maps.newHashMapWithExpectedSize(maxPollRecords);
                        List<Message> messages = Lists.newArrayListWithCapacity(consumerRecords.count());
                        consumerRecords.forEach(record -> {
                            offsets.put(new TopicPartition(record.topic(), record.partition()),
                                    new OffsetAndMetadata(record.offset() + 1));

                            messages.add(Message.builder()
                                    .topic(record.topic())
                                    .partition(record.partition())
                                    .key(record.key())
                                    .body(record.value()).build());
                        });
                        messageQueueConsumer.consume(messages, () -> consumer.commitSync(offsets));
                    } catch (Exception e) {
                        log.error(KAFKA_CONSUMER_PROCESSOR_CONSUME_ERROR, e.getMessage(), e);
                    }
                }
            }).start();
        }
        threadRunning.set(true);
    }

    @Override
    public void destroy() {
        log.debug(DESTROY_KAFKA_CONSUMER);
        threadRunning.set(false);
        consumers.forEach(Consumer::unsubscribe);
        consumers.clear();
    }

    private Consumer<String, String> initKafkaConsumer(MessageQueueConsumer messageQueueConsumer) {
        Class<? extends MessageQueueConsumer> clazz = messageQueueConsumer.getClass();
        MessageQueueListener annotation = clazz.getAnnotation(MessageQueueListener.class);
        if (!matcher.apply(annotation.type())) {
            return null;
        }

        String topic = annotation.topic();

        String group = null;
        if (StringUtils.isNotBlank(kafkaProperties.getConsumer().getGroupId())) {
            group = kafkaProperties.getConsumer().getGroupId() + Strings.UNDERLINE + topic;
        } else if (StringUtils.isNotBlank(annotation.group())) {
            group = annotation.group();
        }

        Consumer<String, String> consumer = consumerFactory.createConsumer(group, kafkaProperties.getClientId());
        consumer.subscribe(Collections.singleton(topic));

        log.debug(CREATE_CONSUMER_FROM_CONSUMER_FACTORY_GROUP_TOPIC, group, topic);
        return consumer;
    }
}
