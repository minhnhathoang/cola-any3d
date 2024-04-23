package org.nhathm.dto.command;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class UserProfileUpdateCmd {

    private String userId;

    private String name;

    private String avatar;

    private String address;

    private String phone;

    private MultipartFile avatarFile;
}
