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


@EnableConfigurationProperties(JwtProperties.class)
@RequiredArgsConstructor
@Slf4j
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@Configuration(proxyBeanMethods = false)
public class JwtAutoConfiguration {

    @ConditionalOnMissingBean
    @Bean
    public JwtTokenProvider jwtTokenProvider(JwtProperties jwtProperties, JwtTokenStore jwtTokenStore) {
        return new JwtTokenProvider(jwtProperties, jwtTokenStore);
    }

    @ConditionalOnMissingBean
    @Bean
    public JwtTokenService jwtTokenService(AuthenticationManager authenticationManager,
                                           JwtTokenProvider jwtTokenProvider) {
        return new JwtTokenService(authenticationManager, jwtTokenProvider);
    }
}
