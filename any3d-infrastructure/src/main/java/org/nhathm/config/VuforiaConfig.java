package org.nhathm.config;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import vuforia.VuforiaApiClient;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Component
@RequiredArgsConstructor
public class VuforiaConfig {

    @Bean
    public VuforiaApiClient vuforiaApiClient() throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        VuforiaApiClient client = new VuforiaApiClient(new OkHttpClient());
        System.out.println("VuforiaApiClient created");
//        System.out.println(client.getAllTargets("df15ae87939e346784dc41d1fc939b27bdb31ac7", "1eb77208f29f898dcf58e6b2625d70964afc5e14"));
        return client;
    }
}
