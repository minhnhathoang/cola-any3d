package org.nhathm.dto.command;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ProjectDeleteCmd {

    private String projectId;
}
