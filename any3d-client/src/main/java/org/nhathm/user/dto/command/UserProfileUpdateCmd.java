package org.nhathm.user.dto.command;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Data
public class UserProfileUpdateCmd {

    @NotEmpty
    private String userId;

    private String name;

    private String avatar;

    private String address;

    private String phone;
}
