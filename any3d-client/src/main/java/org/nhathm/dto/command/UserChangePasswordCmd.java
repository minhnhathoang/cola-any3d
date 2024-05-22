package org.nhathm.dto.command;

import lombok.Data;


@Data
public class UserChangePasswordCmd {

    private String userId;

    private String oldPassword;

    private String newPassword;
}
