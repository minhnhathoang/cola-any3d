package org.nhathm.auth.dto.command;

import com.alibaba.cola.dto.Query;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Data
public class AuthRegisterCmd extends Query {

    @NotBlank
    private String username;

    @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
