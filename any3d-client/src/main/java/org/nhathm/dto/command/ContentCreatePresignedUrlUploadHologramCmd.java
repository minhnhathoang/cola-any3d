package org.nhathm.dto.command;

import lombok.Data;


@Data
public class ContentCreatePresignedUrlUploadHologramCmd {

    private String projectId;

    private String hologramFileName;
}
