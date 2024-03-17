package org.nhathm.domain.auth.domainservice;

import domain.security.common.AccessToken;
import domain.security.common.JwtConstants;
import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import util.Strings;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private JwtConfig jwtConfig;

    private final JwtTokenStore tokenStore;

    private JwtParser jwtParser;

    private Key key;

    private long tokenValidityInMilliseconds;

    private long tokenValidityInMillisecondsForRememberMe;

//	@Override
//	public void afterPropertiesSet() {
//		byte[] keyBytes = jwtConfig.getBase64Secret() != null ?
//			Decoders.BASE64.decode(jwtConfig.getBase64Secret()) :
//			jwtConfig.getSecret().getBytes();
//
//		key = Keys.hmacShaKeyFor(keyBytes);
//		this.jwtParser = Jwts.parser()
//				.verifyWith((SecretKey) key).build();
//		this.tokenValidityInMilliseconds = 1000 * jwtConfig.getTokenValidityInSeconds();
//		this.tokenValidityInMillisecondsForRememberMe = 1000 * jwtConfig.getTokenValidityInSecondsForRememberMe();
//	}

    public AccessToken createToken(Authentication authentication, boolean rememberMe, Map<String, Object> claims) {
        if (CollectionUtils.isNotEmpty(authentication.getAuthorities())) {
            String authorities = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority).collect(Collectors.joining(Strings.COMMA));
            claims.put(JwtConstants.AUTHORITIES_KEY, authorities);
        }

        return createToken(authentication.getName(), rememberMe, claims);
    }

    public AccessToken createToken(String subject, boolean rememberMe, Map<String, Object> claims) {
        long now = (new Date()).getTime();
        Date expiration;
        if (rememberMe) {
            expiration = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else {
            expiration = new Date(now + this.tokenValidityInMilliseconds);
        }

        String value = Jwts
                .builder()
                .setSubject(subject)
                .addClaims(claims)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(expiration)
                .compact();

        AccessToken accessToken = AccessToken.builder()
                .value(value)
                .expiration(expiration)
                .build();
        if (tokenStore != null) {
            tokenStore.storeAccessToken(accessToken);
        }
        return accessToken;
    }

    public void validateToken(@NotNull AccessToken accessToken) {
        try {
            if (tokenStore != null && !tokenStore.validateAccessToken(accessToken)) {
                throw new UnauthorizedException("存储的令牌不存在");
            }
            jwtParser.parseClaimsJws(accessToken.getValue());
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException("令牌已失效");
        } catch (UnsupportedJwtException e) {
            throw new UnauthorizedException("不支持的令牌");
        } catch (MalformedJwtException e) {
            throw new UnauthorizedException("令牌格式错误");
        } catch (SecurityException e) {
            throw new UnauthorizedException("校验令牌不通过");
        } catch (IllegalArgumentException e) {
            throw new UnauthorizedException("校验令牌异常");
        }
    }

    public void clearToken(AccessToken accessToken) {
        if (tokenStore != null) {
            tokenStore.removeAccessToken(accessToken);
        }
    }

    public Authentication getAuthentication(AccessToken accessToken) {
        Claims claims = parseClaims(accessToken);

        Collection<? extends GrantedAuthority> authorities = Collections.emptyList();
        if (claims.containsKey(JwtConstants.AUTHORITIES_KEY)) {
            authorities = Arrays
                    .stream(claims.get(JwtConstants.AUTHORITIES_KEY).toString().split(Strings.COMMA))
                    .filter(auth -> !auth.trim().isEmpty())
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }

        User principal = new User(claims.getSubject(), Strings.EMPTY, authorities);
        return new UsernamePasswordAuthenticationToken(principal, accessToken, authorities);
    }

    public Claims parseClaims(AccessToken accessToken) {
        return jwtParser.parseClaimsJws(accessToken.getValue()).getBody();
    }
}
