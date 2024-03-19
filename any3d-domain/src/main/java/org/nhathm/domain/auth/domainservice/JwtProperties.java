package org.nhathm.domain.auth.domainservice;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = JwtProperties.PREFIX)
public class JwtProperties {

    public static final String PREFIX = "spring.security.jwt";

    private boolean enabled;

    private String header = "Authorization";

    private String base64Secret;

    private String secret;

    private long tokenValidityInSeconds = 1800;

    private long tokenValidityInSecondsForRememberMe = 2592000;

    private List<String> anonymousUrls;

    private List<String> permitAllUrls;

    private List<String> authenticatedUrls;
}
