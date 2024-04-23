package org.nhathm.app.objectstorage.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.minio.messages.EventType;
import io.minio.messages.NotificationRecords;
import lombok.Data;

/**
 * @author nhathm
 */
@Data
public class ObjectStorageEvent extends NotificationRecords {

    @JsonProperty("EventName")
    private EventType eventName;

    @JsonProperty("Key")
    private String key;
}
