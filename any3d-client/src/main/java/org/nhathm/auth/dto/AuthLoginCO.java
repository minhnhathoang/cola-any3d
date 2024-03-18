package org.nhathm.auth.dto;

import com.alibaba.cola.dto.ClientObject;
import domain.security.common.AccessToken;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Builder
@Accessors(chain = true)
public class AuthLoginCO extends ClientObject {

    private String username;

    private AccessToken accessToken;
}
