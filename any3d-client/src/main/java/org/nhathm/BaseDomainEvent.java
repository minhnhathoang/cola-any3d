package org.nhathm;

import lombok.Getter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author nhathm
 */
@Getter
public class BaseDomainEvent implements Serializable {

    private final String id;

    private final Date createdAt;

    public BaseDomainEvent() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = new Date();
    }
}
