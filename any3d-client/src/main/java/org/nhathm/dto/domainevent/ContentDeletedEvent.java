package org.nhathm.dto.domainevent;

import lombok.Data;

/**
 * @author nhathm
 */
@Data
public class ContentDeletedEvent extends BaseEvent {

    private String contentId;
}
