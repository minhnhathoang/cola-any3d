package org.nhathm.dto.clientobject;

import com.alibaba.cola.dto.ClientObject;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class UserCO extends ClientObject {

    private String id;

    private String username;

    private String email;
}
