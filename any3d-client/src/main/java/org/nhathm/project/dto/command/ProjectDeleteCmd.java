package org.nhathm.project.dto.command;

import lombok.Builder;
import lombok.Data;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Data
@Builder
public class ProjectDeleteCmd {

    private String projectId;
}
