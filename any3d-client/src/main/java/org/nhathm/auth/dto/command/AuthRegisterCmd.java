package org.nhathm.auth.dto.command;

import com.alibaba.cola.dto.Command;
import com.alibaba.cola.dto.Query;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Data
public class AuthRegisterCmd extends Query {

    @NotBlank(message = "username can't be blank")
    private String username;

    @NotBlank(message = "password can't be blank")
    private String password;
}
