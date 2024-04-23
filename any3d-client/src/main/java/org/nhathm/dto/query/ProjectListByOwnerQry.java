package org.nhathm.dto.query;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ProjectListByOwnerQry {
    private String ownerId;
}
