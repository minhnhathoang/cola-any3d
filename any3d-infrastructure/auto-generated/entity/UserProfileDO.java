package auto

-generated.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

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

    private String userId;

    private String name;

    private String avatar;

    private String address;

    private String phone;
}
