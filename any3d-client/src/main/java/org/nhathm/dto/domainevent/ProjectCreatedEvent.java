package org.nhathm.dto.domainevent;

import lombok.Data;

/**
 * @author nhathm
 */
@Data
public class ProjectCreatedEvent extends BaseEvent {

    private String projectId;
}
