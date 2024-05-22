package org.nhathm.dto.command;

import lombok.Data;

@Data
public class ProjectUpdateCmd {

    private String id;

    private String name;

    private String description;
}
