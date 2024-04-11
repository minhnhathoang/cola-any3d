package org.nhathm.user.dto.command;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Data
public class UserProfileUpdateCmd {

    private Long userId;

    private String name;

    private String avatar;

    private String address;

    private String phone;

    private MultipartFile avatarFile;
}
