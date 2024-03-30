package org.nhathm.domain.auth.domainservice;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import domain.security.common.AccessToken;
import domain.security.common.JwtConstants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import util.Strings;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider implements InitializingBean {

    @Value("${ejwt.public.key}")
    RSAPublicKey rsaPublicKey;

    @Value("${ejwt.private.key}")
    RSAPrivateKey rsaPrivateKey;

    private final JwtConfig jwtConfig;

    private final JwtTokenStore tokenStore;

    private JWSVerifier jwsVerifier;

    private long tokenValidityInMilliseconds;

    private long tokenValidityInMillisecondsForRememberMe;

    @Override
    public void afterPropertiesSet() {
        this.jwsVerifier = new RSASSAVerifier(rsaPublicKey);
        this.tokenValidityInMilliseconds = 1000 * jwtConfig.getTokenValidityInSeconds();
        this.tokenValidityInMillisecondsForRememberMe = 1000 * jwtConfig.getTokenValidityInSecondsForRememberMe();
    }

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
        JWSHeader header = new JWSHeader(JWSAlgorithm.RS256);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(subject)
                .issuer("any3d-nhathm")
                .issueTime(new Date())
                .claim(JwtConstants.AUTHORITIES_KEY, claims.get(JwtConstants.AUTHORITIES_KEY))
                .expirationTime(expiration)
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new RSASSASigner(rsaPrivateKey, true));
            AccessToken accessToken = AccessToken.builder()
                    .value(jwsObject.serialize())
                    .expiration(expiration)
                    .build();
            if (tokenStore != null) {
                tokenStore.storeAccessToken(accessToken);
            }
            return accessToken;
        } catch (Exception e) {
            log.error("生成令牌失败", e);
            throw new UnauthorizedException("生成令牌失败");
        }
    }

    public boolean validateToken(@NotNull AccessToken accessToken) {
        if (tokenStore != null && !tokenStore.validateAccessToken(accessToken)) {
            throw new UnauthorizedException("存储的令牌不存在");
        }
        try {
            SignedJWT signedJWT = SignedJWT.parse(accessToken.getValue());
            return signedJWT.verify(this.jwsVerifier);

        } catch (ParseException | JOSEException e) {
            log.error("JWT", e);
            throw new UnauthorizedException("JWT");
        }
    }

    public void clearToken(AccessToken accessToken) {
        if (tokenStore != null) {
            tokenStore.removeAccessToken(accessToken);
        }
    }

    public Authentication getAuthentication(AccessToken accessToken) {
        JWTClaimsSet claimsSet = parseClaimsSet(accessToken);

        Collection<? extends GrantedAuthority> authorities = Collections.emptyList();
        if (claimsSet.getClaims().containsKey(JwtConstants.AUTHORITIES_KEY)) {
            authorities = Arrays
                    .stream(claimsSet.getClaims().get(JwtConstants.AUTHORITIES_KEY).toString().split(Strings.COMMA))
                    .filter(auth -> !auth.trim().isEmpty())
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
        User principal = new User(claimsSet.getSubject(), Strings.EMPTY, authorities);
        return new UsernamePasswordAuthenticationToken(principal, accessToken, authorities);
    }

    public JWTClaimsSet parseClaimsSet(AccessToken accessToken) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(accessToken.getValue());
            return signedJWT.getJWTClaimsSet();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
