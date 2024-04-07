package org.nhathm.domain.userprofile.entity;

import lombok.Data;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Data
public class UserProfile {

    private Long userId;

    private String name;

    private String avatar;

    private String address;

    private String phone;
}
