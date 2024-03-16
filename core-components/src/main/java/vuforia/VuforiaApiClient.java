package vuforia;


import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import util.DateUtils;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
public class VuforiaApiClient {

    private final OkHttpClient client;

    public String getAllTargets(String serverAccessKey, String serverSecretKey) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        String date = DateUtils.formatDate(new Date());
        String signature = VuforiaSignature.generateSignature("/targets", "GET", "", "", date, serverSecretKey);

        Request request = new Request.Builder()
                .url("https://vws.vuforia.com/targets")
                .header("Date", date)
                .header("Authorization", "VWS " + serverAccessKey + ":" + signature)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
