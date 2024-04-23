package org.nhathm.domain.vuforia;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.DateUtils;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.nhathm.domain.arsdk.VuforiaAddTargetResponse;
import org.nhathm.domain.userprofile.domainservice.VuforiaApiService;
import org.nhathm.domain.vuforia.entity.VuforiaKey;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import util.error.ThirdServiceException;
import util.json.JsonUtils;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.UUID;

@Log4j2
@Component
public class VuforiaApiServiceImpl implements VuforiaApiService {

    private final String BASE_URL = "https://vws.vuforia.com";

    public VuforiaAddTargetResponse addTarget(VuforiaKey key, MultipartFile multipartFile) {
        HttpPost postRequest = new HttpPost();
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            postRequest.setURI(new URI(BASE_URL + "/targets"));

            // body
            JSONObject requestBody = new JSONObject();
            byte[] image = multipartFile.getBytes();
            requestBody.put("name", UUID.randomUUID());
            requestBody.put("width", 1);
            requestBody.put("image", Base64.encodeBase64String(image));
            postRequest.setEntity(new StringEntity(requestBody.toString()));

            // header
            setHeaders(postRequest, key);

            HttpResponse response = client.execute(postRequest);
            String responseBody = EntityUtils.toString(response.getEntity());
            log.info("[Vuforia API Add Target] response: {}", responseBody);
            return JsonUtils.entity(responseBody, VuforiaAddTargetResponse.class);
        } catch (Exception e) {
            throw new ThirdServiceException("Failed to add image target to Vuforia", e);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                log.error("Failed to close http client", e);
            }
        }
    }

    private void setHeaders(HttpUriRequest request, VuforiaKey key) {
        VuforiaSignatureBuilder sb = new VuforiaSignatureBuilder();
        request.setHeader(new BasicHeader("Date", DateUtils.formatDate(new Date()).replaceFirst("[+]00:00$", "")));
        request.setHeader(new BasicHeader("Content-Type", "application/json"));
        request.setHeader("Authorization", "VWS " + key.getAccessKey() + ":" + sb.tmsSignature(request, key.getSecretKey()));
    }
}
