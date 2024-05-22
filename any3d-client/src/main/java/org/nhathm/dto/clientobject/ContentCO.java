package org.nhathm.dto.clientobject;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ContentCO {

    private String id;

    private String projectId;

    private String name;

    private String metadata;

    private HologramCO hologram;

    private ImageTargetCO imageTarget;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedAt;

    private UserCO owner;
}
