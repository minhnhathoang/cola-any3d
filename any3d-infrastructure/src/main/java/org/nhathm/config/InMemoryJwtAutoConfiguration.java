package org.nhathm.config;

import domain.security.common.AccessToken;
import org.nhathm.domain.auth.domainservice.JwtTokenStore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class InMemoryJwtAutoConfiguration {

    @ConditionalOnMissingBean
    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore() {

            @Override
            public boolean validateAccessToken(AccessToken token) {
                return true;
            }

            @Override
            public void storeAccessToken(AccessToken token) {
            }

            @Override
            public void removeAccessToken(AccessToken token) {
            }
        };
    }
}