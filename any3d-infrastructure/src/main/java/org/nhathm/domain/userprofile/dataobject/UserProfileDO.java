package org.nhathm.domain.userprofile.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author nhathm
 * @since 2024-04-06
 */
@Getter
@Setter
@TableName("user_profile")
public class UserProfileDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String name;

    private String avatar;

    private String address;

    private String phone;
}
