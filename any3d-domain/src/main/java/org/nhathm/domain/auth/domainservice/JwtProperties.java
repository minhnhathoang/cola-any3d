package org.nhathm.domain.auth.domainservice;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;

@Data
@ConfigurationProperties(prefix = JwtProperties.PREFIX)
public class JwtProperties {

    public static final String PREFIX = "spring.security.jwt";
    @Value("${spring.security.jwt.public.key}")
    RSAPublicKey rsaPublicKey;
    @Value("${spring.security.jwt.private.key}")
    RSAPrivateKey rsaPrivateKey;
    private String header = "Authorization";
    private long tokenValidityInSeconds = 1_800;
    private long tokenValidityInSecondsForRememberMe = 2_592_000;
    private List<String> anonymousUrls;
    private List<String> permitAllUrls;
    private List<String> authenticatedUrls;
}
