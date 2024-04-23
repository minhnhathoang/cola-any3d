package org.nhathm.dto.domainevent;

import lombok.Data;
import org.nhathm.BaseDomainEvent;

/**
 * @author nhathm
 */
@Data
public class ProjectCreatedEvent extends BaseDomainEvent {

    private String projectId;
}
