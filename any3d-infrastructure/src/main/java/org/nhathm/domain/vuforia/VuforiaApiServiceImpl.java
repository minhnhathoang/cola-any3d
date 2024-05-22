package org.nhathm.domain.vuforia;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.DateUtils;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.nhathm.domain.arsdk.VuforiaAddTargetResponse;
import org.nhathm.domain.arsdk.VuforiaDeleteTargetResponse;
import org.nhathm.domain.arsdk.VuforiaGetSummaryResponse;
import org.nhathm.domain.arsdk.VuforiaGetTargetResponse;
import org.nhathm.domain.userprofile.domainservice.VuforiaApiService;
import org.nhathm.domain.vuforia.entity.VuforiaKey;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import util.error.ThirdServiceException;
import util.json.JsonUtils;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.DateUtils;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.UUID;

@Log4j2
@Component
public class VuforiaApiServiceImpl implements VuforiaApiService {

    private final String BASE_URL = "https://vws.vuforia.com";


    public VuforiaGetSummaryResponse getSummary(VuforiaKey key) {
        try {
            HttpGet getRequest = new HttpGet();
            CloseableHttpClient client = HttpClients.createDefault();
            getRequest.setURI(new URI(BASE_URL + "/summary"));
            setHeadersWithoutContentType(getRequest, key);
            HttpResponse response = client.execute(getRequest);
            String responseBody = EntityUtils.toString(response.getEntity());
            log.info("[Vuforia API Get Summary] response: {}", responseBody);
            return JsonUtils.entity(responseBody, VuforiaGetSummaryResponse.class);
        } catch (Exception e) {
            throw new ThirdServiceException("Failed to get summary from Vuforia", e);
        }
    }

    public void getTargets(VuforiaKey key) {
        try {
            HttpGet getRequest = new HttpGet();
            CloseableHttpClient client = HttpClients.createDefault();
            getRequest.setURI(new URI(BASE_URL + "/targets"));
            setHeadersWithoutContentType(getRequest, key);

            HttpResponse response = client.execute(getRequest);
            String responseBody = EntityUtils.toString(response.getEntity());
            log.info("[Vuforia API Get Targets] response: {}", responseBody);
        } catch (Exception e) {
            throw new ThirdServiceException("Failed to get targets from Vuforia", e);
        }
    }

    public VuforiaGetTargetResponse getTarget(VuforiaKey key, String targetId) {
        try {
            HttpGet getRequest = new HttpGet();
            CloseableHttpClient client = HttpClients.createDefault();
            getRequest.setURI(new URI(BASE_URL + "/targets/" + targetId));
            setHeadersWithoutContentType(getRequest, key);

            HttpResponse response = client.execute(getRequest);
            String responseBody = EntityUtils.toString(response.getEntity());
            log.info("[Vuforia API Get Target] response: {}", responseBody);
            return JsonUtils.entity(responseBody, VuforiaGetTargetResponse.class);
        } catch (Exception e) {
            throw new ThirdServiceException("Failed to get target from Vuforia", e);
        }
    }

    public VuforiaAddTargetResponse addTarget(VuforiaKey key, MultipartFile multipartFile) {
        try {
            HttpPost postRequest = new HttpPost();
            CloseableHttpClient client = HttpClients.createDefault();
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
        }
    }

    @Override
    public boolean isValidKey(VuforiaKey key) {
        return getSummary(key).isSuccess();
    }

    public VuforiaDeleteTargetResponse deleteTarget(VuforiaKey key, String targetId) {
        HttpDelete deleteRequest = new HttpDelete();
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            deleteRequest.setURI(new URI(BASE_URL + "/targets/" + targetId));
            setHeadersWithoutContentType(deleteRequest, key);
            HttpResponse response = client.execute(deleteRequest);
            String responseBody = EntityUtils.toString(response.getEntity());
            log.info("[Vuforia API Delete Target] response: {}", responseBody);
            return JsonUtils.entity(responseBody, VuforiaDeleteTargetResponse.class);
        } catch (Exception e) {
            throw new ThirdServiceException("Failed to delete target from Vuforia", e);
        }
    }

    private void setHeadersWithoutContentType(HttpUriRequest request, VuforiaKey key) {
        VuforiaSignatureBuilder sb = new VuforiaSignatureBuilder();
        request.setHeader(new BasicHeader("Date", DateUtils.formatDate(new Date()).replaceFirst("[+]00:00$", "")));
        request.setHeader("Authorization", "VWS " + key.getAccessKey() + ":" + sb.tmsSignature(request, key.getSecretKey()));
    }

    private void setHeaders(HttpUriRequest request, VuforiaKey key) {
        VuforiaSignatureBuilder sb = new VuforiaSignatureBuilder();
        request.setHeader(new BasicHeader("Date", DateUtils.formatDate(new Date()).replaceFirst("[+]00:00$", "")));
        request.setHeader(new BasicHeader("Content-Type", "application/json"));
        request.setHeader("Authorization", "VWS " + key.getAccessKey() + ":" + sb.tmsSignature(request, key.getSecretKey()));
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        VuforiaApiServiceImpl vuforiaApiService = new VuforiaApiServiceImpl();
        VuforiaKey key = new VuforiaKey();
        key.setAccessKey("15e682b683bfed7edb928b2db3ce636b731f5afe");
        key.setSecretKey("df9655cc1bd2310100484297d5b1851b3c9cb785");

        VuforiaGetSummaryResponse summary = vuforiaApiService.getSummary(key);
        log.info("summary: {}", summary);
        log.info(summary.isSuccess());

//        vuforiaApiService.deleteTarget(key, "51c80b897ab9494abe33db93f33053f6");

        vuforiaApiService.getTargets(key);
        vuforiaApiService.getTarget(key, "1ccde489c7554fa6bbe4d54a4ca4b4fc");
    }
}
