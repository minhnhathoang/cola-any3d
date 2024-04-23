package auto

-generated.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@TableName("user")
public class UserDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

    private String hashedPassword;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedAt;
}
