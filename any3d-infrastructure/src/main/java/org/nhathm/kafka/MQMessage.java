package org.nhathm.kafka;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author nhathm
 */
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class MQMessage implements Serializable {

    private String namespace;

    private String topic;

    private Integer partition;

    private String key;

    private String tags;

    private String body;

    @Builder.Default
    private Integer delayTimeLevel = 0;

}
