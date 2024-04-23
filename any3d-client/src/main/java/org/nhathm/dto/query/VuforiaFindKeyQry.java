package org.nhathm.dto.query;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class VuforiaFindKeyQry {

    @NotNull
    private String projectId;
}
