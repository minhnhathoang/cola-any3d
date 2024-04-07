package org.nhathm.user.dto.command;

import lombok.Data;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Data
public class UserProfileUpdateCmd {

    private String userId;

    private String username;

    private String email;

    private String password;
}
