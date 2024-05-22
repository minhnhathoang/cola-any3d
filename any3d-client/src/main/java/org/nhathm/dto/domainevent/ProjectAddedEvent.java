package org.nhathm.dto.domainevent;

import lombok.Data;

/**
 * @author nhathm
 */
@Data
public class ProjectAddedEvent extends BaseEvent {

    private String projectId;
}
