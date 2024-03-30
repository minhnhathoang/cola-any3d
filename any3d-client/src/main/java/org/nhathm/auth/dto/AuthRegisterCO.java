package org.nhathm.auth.dto;

import com.alibaba.cola.dto.ClientObject;
import lombok.Data;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Data
public class AuthRegisterCO extends ClientObject {
    private String username;
}
