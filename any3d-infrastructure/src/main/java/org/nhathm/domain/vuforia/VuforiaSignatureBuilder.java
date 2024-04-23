package org.nhathm.domain.vuforia;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SignatureException;

@Component
@Log4j2
public class VuforiaSignatureBuilder {

    public static String calculateRFC2104HMAC(String key, String data) throws SignatureException {
        String result = "";
        try {
            // get an hmac_sha1 key from the raw key bytes
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");

            // get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);

            // compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(data.getBytes());

            // base64-encode the hmac
            result = new String(Base64.encodeBase64(rawHmac, false));

        } catch (Exception e) {
            log.error("Error while calculating RFC2104 HMAC", e);
        }
        return result;
    }

    public String tmsSignature(HttpUriRequest request, String secretKey) {
        String method = request.getMethod();
        String contentType = "";
        String hexDigest = "d41d8cd98f00b204e9800998ecf8427e"; // Hex digest of an empty string

        if (method.equalsIgnoreCase("GET") || method.equalsIgnoreCase("DELETE")) {
            // Do nothing because the strings are already set correctly
        } else if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT")) {
            contentType = "application/json";
            // If this is a POST or PUT the request should have a request body
            hexDigest = contentMD5((HttpEntityEnclosingRequestBase) request);
        } else {
            log.error("ERROR: Invalid content type passed to Sig Builder");
        }

        // Date in the header and date used to calculate the hash must be the same
        String dateValue = request.getFirstHeader("Date").getValue();
        String requestPath = request.getURI().getPath();
        String toDigest = method + "\n" + hexDigest + "\n" + contentType + "\n" + dateValue + "\n" + requestPath;
        String shaHashed = "";
        try {
            System.out.println(toDigest);
            shaHashed = calculateRFC2104HMAC(secretKey, toDigest);
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return shaHashed;
    }

    private String contentMD5(HttpEntityEnclosingRequestBase httpMethod) {
        ByteArrayOutputStream requestOutputStream = new ByteArrayOutputStream();
        try {
            httpMethod.getEntity().writeTo(requestOutputStream);
        } catch (IOException e) {
            log.error("ERROR: IOException caught when writing Content MD5 hash", e);
        }
        return DigestUtils.md5Hex(requestOutputStream.toByteArray()).toLowerCase();
    }
}
