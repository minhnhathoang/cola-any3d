package org.nhathm.domain.auth.domainservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.authentication.AuthenticationManager;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@EnableConfigurationProperties(JwtProperties.class)
@RequiredArgsConstructor
@Slf4j
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@Configuration(proxyBeanMethods = false)
public class JwtAutoConfiguration {
    private final JwtProperties jwtProperties;

    @ConditionalOnMissingBean
    @Bean
    public JwtTokenProvider jwtTokenProvider(JwtTokenStore jwtTokenStore) {
        JwtConfig jwtConfig = JwtConfig.builder()
                .header(jwtProperties.getHeader())
                .secret(jwtProperties.getSecret())
                .base64Secret(jwtProperties.getBase64Secret())
                .tokenValidityInSeconds(jwtProperties.getTokenValidityInSeconds())
                .tokenValidityInSecondsForRememberMe(jwtProperties.getTokenValidityInSecondsForRememberMe())
                .anonymousUrls(jwtProperties.getAnonymousUrls())
                .authenticatedUrls(jwtProperties.getAuthenticatedUrls())
                .permitAllUrls(jwtProperties.getPermitAllUrls())
                .build();
        return new JwtTokenProvider(jwtConfig, jwtTokenStore);
    }

    @ConditionalOnMissingBean
    @Bean
    public JwtTokenService jwtTokenService(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        return new JwtTokenService(authenticationManager, jwtTokenProvider);
    }
}
