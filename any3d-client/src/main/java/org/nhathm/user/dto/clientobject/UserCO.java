package org.nhathm.user.dto.clientobject;

import com.alibaba.cola.dto.ClientObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserCO extends ClientObject {

    private Long id;

    private String username;

    private String email;
}
