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

package org.nhathm.domain.auth.domainservice;

import com.google.common.collect.Maps;
import domain.security.common.AccessToken;
import lombok.RequiredArgsConstructor;
import org.nhathm.domain.user.entity.UserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class JwtTokenService {

    private static final String AUTHENTICATE_BAD_CREDENTIALS = "JWT authenticated failed due to bad credentials：{}";

    private static final String AUTHENTICATE_EXCEPTION = "JWT authenticated failed, caught exception: {}";

    private final AuthenticationManager authManager;

    private final JwtTokenProvider jwtTokenProvider;

    public AccessToken authenticate(UserDetails user, Map<String, Object> claims) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            Authentication authentication = this.authManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            if (authentication.getAuthorities() != null) {
                if (claims == null) {
                    claims = Maps.newHashMap();
                }
//                StringBuilder authorities = new StringBuilder();
//                for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
//                    authorities.append(grantedAuthority.getAuthority()).append(Strings.COMMA);
//                }
//                authorities.deleteCharAt(authorities.length() - 1);
//                claims.put(JwtConstants.AUTHORITIES_KEY, authorities);
            }
            return jwtTokenProvider.createToken(authentication, user.isRememberMe(), claims);
        } catch (BadCredentialsException ex) {
            throw new UnauthorizedException(ex.getMessage());
        } catch (Exception ex) {
            throw ex;
        }
    }
}
