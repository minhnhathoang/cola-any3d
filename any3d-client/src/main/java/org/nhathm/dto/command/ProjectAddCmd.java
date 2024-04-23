package org.nhathm.dto.command;

import lombok.Data;


@Data
public class ProjectAddCmd {

    private String ownerId;

    private String name;

    private String description;
}
