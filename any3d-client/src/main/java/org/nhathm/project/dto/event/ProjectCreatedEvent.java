package org.nhathm.project.dto.event;

import lombok.Data;
import org.nhathm.BaseDomainEvent;

/**
 * @author nhathm
 */
@Data
public class ProjectCreatedEvent extends BaseDomainEvent {

    private Long projectId;
}
