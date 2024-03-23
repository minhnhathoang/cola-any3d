package org.nhathm.integration.mq;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class MessageSendResult {

    private String topic;

    private Integer partition;

    private Long offset;

    private String transactionId;
}
