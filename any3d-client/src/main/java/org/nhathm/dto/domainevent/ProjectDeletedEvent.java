package org.nhathm.dto.domainevent;

import lombok.Data;

/**
 * @author nhathm
 */
@Data
public class ProjectDeletedEvent extends BaseEvent {

    private String id;

    private String name;

    private String deletedBy;
}
