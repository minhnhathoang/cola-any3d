package org.nhathm.integration.mq.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.nhathm.integration.mq.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
public class KafkaProvider implements MessageQueueProvider {

    private static final String KAFKA_PROVIDER_SEND_INTERRUPTED = "KafkaProvider send interrupted: {}";

    private static final String KAFKA_PROVIDER_CONSUME_ERROR = "KafkaProvider send error: {}";

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public String messageQueueType() {
        return MessageQueueType.KAFKA.name();
    }

    @Override
    public MessageSendResult syncSend(Message message) {
        try {
            CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(message.getTopic(), message.getBody());
            SendResult<String, String> sendResult = future.get();
            return transfer(sendResult);
        } catch (InterruptedException e) {
            log.error(KAFKA_PROVIDER_SEND_INTERRUPTED, e.getMessage(), e);
            Thread.currentThread().interrupt();
            throw new MessageSendException(e.getMessage());
        } catch (Exception e) {
            log.error(KAFKA_PROVIDER_CONSUME_ERROR, e.getMessage(), e);
            throw new MessageSendException(e.getMessage());
        }
    }

    @Override
    public void asyncSend(Message message, MessageSendCallback messageCallback) {
        try {
            CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(message.getTopic(), message.getBody());
            future.whenComplete((sendResult, throwable) -> {
                if (throwable != null) {
                    messageCallback.onFailed(throwable);
                } else {
                    messageCallback.onSuccess(transfer(sendResult));
                }
            });
        } catch (Exception e) {
            log.error(KAFKA_PROVIDER_CONSUME_ERROR, e.getMessage(), e);
            throw new MessageSendException(e.getMessage());
        }
    }

    private MessageSendResult transfer(SendResult<String, String> sendResult) {
        ProducerRecord<String, String> producerRecord = sendResult.getProducerRecord();
        RecordMetadata recordMetadata = sendResult.getRecordMetadata();
        return MessageSendResult.builder()
                .topic(producerRecord.topic())
                .partition(recordMetadata.partition())
                .offset(recordMetadata.offset())
                .build();
    }
}
