package org.nhathm.domain.objectstorage.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.minio.messages.EventType;
import io.minio.messages.NotificationRecords;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author nhathm
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ObjectStorageEvent extends NotificationRecords {

    @JsonProperty("EventName")
    private EventType eventName;

    @JsonProperty("Key")
    private String key;
}
