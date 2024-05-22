package org.nhathm.domain.userprofile.entity;

import com.alibaba.cola.domain.Entity;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


@Data
@Entity
public class UserProfile {

    private String id;

    private String userId;

    private String name;

    private String avatar;

    private String address;

    private String phone;
}
