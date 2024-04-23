package org.nhathm.dto.clientobject;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ProjectCO {

    private String id;

    private UserCO owner;

    private String name;

    private String description;

    private String metadata;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedAt;
}
