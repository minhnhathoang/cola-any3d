package org.nhathm.dto.command;

import lombok.Data;

@Data
public class VuforiaUpdateKeyCmd {

    private String projectId;

    private String accessKey;

    private String secretKey;
}
