package org.nhathm.project.dto.command;

import lombok.Data;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Data
public class ContentCreatePresignedUploadUrlCmd {

    private Long projectId;

    private String hologramFileName;
}
