package org.nhathm.dto.command;

import lombok.Data;

@Data
public class ContentUpdateCmd {

    private String id;

    private String name;

    private String metadata;
}
