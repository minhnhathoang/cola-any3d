package org.nhathm.integration.mq;

import org.springframework.stereotype.Component;
import util.Strings;

import java.lang.annotation.*;

@Component
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageQueueListener {

    String type() default Strings.EMPTY;

    String group() default Strings.EMPTY;

    String topic() default Strings.EMPTY;

    int pullBatchSize() default 0;

    int consumeMessageBatchMaxSize() default 0;

    int consumeThreadMin() default 0;

    int consumeThreadMax() default 0;

    long consumeTimeout() default 0;

    ConsumeMode consumeMode() default ConsumeMode.UNSET;

    MessageModel messageModel() default MessageModel.UNSET;

    MessageSelectorType selectorType() default MessageSelectorType.UNSET;

    String selectorExpression() default "*";

    boolean enableMsgTrace() default true;
}
