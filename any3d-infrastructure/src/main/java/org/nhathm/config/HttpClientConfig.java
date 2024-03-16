package org.nhathm.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
public class HttpClientConfig {

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }
}
