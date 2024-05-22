package org.nhathm.domain.userprofile.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.nhathm.domain.user.dataobject.UserDO;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author nhathm
 * @since 2024-04-06
 */
@Data
@TableName(value = "user_profile", autoResultMap = true)
public class UserProfileDO {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField
    private String userId;

    @TableField
    private String name;

    @TableField
    private String avatar;

    @TableField
    private String address;

    @TableField
    private String phone;
}
