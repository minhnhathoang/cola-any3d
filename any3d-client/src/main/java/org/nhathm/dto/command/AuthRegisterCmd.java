package org.nhathm.dto.command;

import com.alibaba.cola.dto.Query;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Data
public class AuthRegisterCmd extends Query {

    @NotBlank
    private String username;

    @NotBlank
    @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @NotBlank
    private String password;
}
