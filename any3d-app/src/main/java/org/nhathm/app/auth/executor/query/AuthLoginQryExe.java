/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.nhathm.app.auth.executor.query;

import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.ErrorCode;
import org.nhathm.auth.dto.AuthLoginCO;
import org.nhathm.auth.dto.query.AuthLoginQry;
import org.nhathm.domain.auth.domainservice.JwtTokenService;
import org.nhathm.domain.auth.gateway.AuthGateway;
import org.nhathm.domain.user.entity.User;
import org.nhathm.domain.user.entity.UserDetails;
import org.springframework.stereotype.Component;
import util.error.ClientAssert;

@RequiredArgsConstructor
@Component
public class AuthLoginQryExe {

    private final AuthGateway authGateway;

    private final JwtTokenService jwtTokenService;

    public SingleResponse<AuthLoginCO> execute(AuthLoginQry qry) {
        User user = authGateway.login(qry.getUsername(), qry.getPassword());
        ClientAssert.notNull(user, ErrorCode.B_AUTH_Unauthorized.toBizException());
        return SingleResponse.of(AuthLoginCO.builder()
                .username(user.getUsername())
                .accessToken(
                        jwtTokenService.authenticate(UserDetails.builder()
                                .username(user.getUsername())
                                .password(qry.getPassword())
                                .build(), null)
                )
                .build());
    }
}
