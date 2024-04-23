package org.nhathm.dto.query;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class AuthLoginQry {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
