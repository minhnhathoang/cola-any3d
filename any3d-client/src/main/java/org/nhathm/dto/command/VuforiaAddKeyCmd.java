package org.nhathm.dto.command;

import lombok.Data;

@Data
public class VuforiaAddKeyCmd {

    private String projectId;

    private String accessKey;

    private String secretKey;
}
