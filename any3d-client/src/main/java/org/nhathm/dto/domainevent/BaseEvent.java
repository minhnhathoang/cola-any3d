package org.nhathm.dto.domainevent;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author nhathm
 */
@Data
public class BaseEvent implements Serializable {

    private final String id;

    private final Date createdAt;

    private final Class<?> type = this.getClass();

    protected Object data;

    public BaseEvent() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = new Date();
    }
}