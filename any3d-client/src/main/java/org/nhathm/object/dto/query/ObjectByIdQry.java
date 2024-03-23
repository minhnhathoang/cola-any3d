package org.nhathm.object.dto.query;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Data
@Builder
public class ObjectByIdQry {

    @NotNull(message = "userId can't be null")
    private String userId;
}
