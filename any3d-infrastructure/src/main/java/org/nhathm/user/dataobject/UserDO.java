package org.nhathm.user.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@TableName("users")
public class UserDO {

    @TableId("id")
    private String id;

    @TableField("email")
    private String email;

    @TableField("username")
    private String username;

    @TableField("hashed_password")
    private String hashedPassword;
}
