package org.nhathm.user.dto.clientobject;

import com.alibaba.cola.dto.ClientObject;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author nhathm
 */
@Data
public class UserProfileCO extends ClientObject {

    @NotEmpty
    private Long userId;

    private String name;

    private String avatar;

    private String address;

    private String phone;
}
