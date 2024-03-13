package org.nhathm.user.dto.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Data
public class UserAddCmd {

    @NotBlank(message = "username can't be blank")
    private String username;

    @NotBlank(message = "email can't be blank")
    private String email;

    @NotBlank(message = "password can't be blank")
    private String password;
}
