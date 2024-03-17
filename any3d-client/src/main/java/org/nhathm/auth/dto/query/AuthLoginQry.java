package org.nhathm.auth.dto.query;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Data
public class AuthLoginQry {

    @NotNull
    private String email;

    @NotNull
    private String password;
}
