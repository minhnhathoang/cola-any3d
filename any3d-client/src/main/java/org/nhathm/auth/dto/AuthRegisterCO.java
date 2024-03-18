package org.nhathm.auth.dto;

import com.alibaba.cola.dto.ClientObject;
import domain.security.common.AccessToken;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Data
public class AuthRegisterCO extends ClientObject {
    private String username;
}
