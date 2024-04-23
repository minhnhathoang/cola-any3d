package org.nhathm.dto.clientobject;

import com.alibaba.cola.dto.ClientObject;
import domain.security.common.AccessToken;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class AuthLoginCO extends ClientObject {

    private AccessToken accessToken;
}
