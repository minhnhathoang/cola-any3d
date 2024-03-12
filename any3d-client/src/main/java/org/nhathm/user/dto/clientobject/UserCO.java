package org.nhathm.user.dto.clientobject;

import com.alibaba.cola.dto.ClientObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserCO extends ClientObject {

    @NotEmpty
    private String userId;
}
