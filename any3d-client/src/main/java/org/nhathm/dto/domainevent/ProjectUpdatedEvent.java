package org.nhathm.dto.domainevent;

import lombok.Data;

/**
 * @author nhathm
 */
@Data
public class ProjectUpdatedEvent extends BaseEvent {

    private String projectId;

}
