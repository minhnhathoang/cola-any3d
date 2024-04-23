package org.nhathm.domain.userprofile.entity;

import com.alibaba.cola.domain.Entity;
import lombok.Data;


@Data
@Entity
public class UserProfile {

    private String userId;

    private String name;

    private String avatar;

    private String address;

    private String phone;
}
