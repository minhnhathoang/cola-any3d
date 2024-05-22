package org.nhathm.dto.clientobject;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class ProjectCO {

    private String id;

    private UserCO owner;

    private String name;

    private String description;

    private String metadata;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedAt;

    private int contentCount;

    private VuforiaKeyCO vuforiaKey;
}
