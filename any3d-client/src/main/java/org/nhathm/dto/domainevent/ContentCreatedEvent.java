package org.nhathm.dto.domainevent;

import lombok.Data;

/**
 * @author nhathm
 */
@Data
public class ContentCreatedEvent extends BaseEvent {

    private String contentId;

    private String projectId;
}
