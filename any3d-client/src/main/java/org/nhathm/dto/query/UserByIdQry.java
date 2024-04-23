package org.nhathm.dto.query;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
@Builder
public class UserByIdQry {

    @NotNull(message = "userId can't be null")
    private String userId;
}
