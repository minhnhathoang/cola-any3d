package org.nhathm.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@EnableJpaAuditing(auditorAwareRef = "customAuditorAware")
@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OkHttpClient httpClient() {
        return new OkHttpClient();
    }

    @Bean
    public AuditorAware<String> customAuditorAware() {
        return new AuditorAware<String>() {
            @Override
            public Optional<String> getCurrentAuditor() {
                return Optional.empty();
            }
        };
    }

//    @Bean
//    public FilterChainProxy filterChainProxy(SecurityFilterChain chain) {
//        return new FilterChainProxy(chain);
//    }
}
