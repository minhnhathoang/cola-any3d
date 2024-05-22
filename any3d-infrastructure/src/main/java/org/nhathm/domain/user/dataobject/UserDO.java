package org.nhathm.domain.user.dataobject;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.nhathm.domain.userprofile.entity.UserProfile;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author nhathm
 * @since 2024-04-06
 */
@Data
@TableName(value = "user", autoResultMap = true)
public class UserDO {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField
    private String username;

    @TableField
    private String hashedPassword;

    @TableField
    private String email;

    @TableField
    private LocalDateTime createdAt;

    @TableField
    private LocalDateTime lastModifiedAt;
}
