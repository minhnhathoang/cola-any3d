package org.nhathm.dto.query;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ProjectGetByIdQry {

    private String projectId;
}
