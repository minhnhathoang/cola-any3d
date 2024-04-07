package org.nhathm.auth.dto;

import com.alibaba.cola.dto.ClientObject;
import domain.security.common.AccessToken;
import lombok.Builder;
import lombok.Getter;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Builder
@Getter
public class AuthLoginCO extends ClientObject {

    private AccessToken accessToken;
}
