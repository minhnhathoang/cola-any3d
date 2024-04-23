package org.nhathm.app.executor;

import com.alibaba.cola.dto.SingleResponse;
import domain.security.common.AccessToken;
import lombok.RequiredArgsConstructor;
import org.nhathm.domain.auth.domainservice.JwtTokenService;
import org.nhathm.domain.user.entity.User;
import org.nhathm.dto.clientobject.AuthLoginCO;
import org.nhathm.dto.query.AuthLoginQry;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthLoginQryExe {

    private final JwtTokenService jwtTokenService;

    public SingleResponse<AuthLoginCO> execute(AuthLoginQry qry) {
        User user = new User();
        BeanUtils.copyProperties(qry, user);
        AccessToken accessToken = jwtTokenService.authenticate(user, null);
        return SingleResponse.of(AuthLoginCO.builder()
                .accessToken(accessToken)
                .build());
    }
}
