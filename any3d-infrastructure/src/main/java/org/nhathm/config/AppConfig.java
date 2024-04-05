package org.nhathm.config;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Configuration
@RequiredArgsConstructor
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OkHttpClient httpClient() {
        return new OkHttpClient();
    }
}
