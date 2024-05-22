package org.nhathm.dto.command;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class VuforiaAddKeyCmd {

    @NotEmpty
    private String projectId;

    @NotEmpty
    private String accessKey;

    @NotEmpty
    private String secretKey;
}
