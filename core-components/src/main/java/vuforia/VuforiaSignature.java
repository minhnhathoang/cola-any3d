package vuforia;

import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Locale;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
public class VuforiaSignature {
    private static final String BASE_URL = "https://vws.vuforia.com";

    public static String generateSignature(
            String requestPath,
            String method,
            String content,
            String contentType,
            String date,
            String serverSecretKey
    ) throws NoSuchAlgorithmException, InvalidKeyException {
        String contentMD5 = DigestUtils.md5Hex(content).toLowerCase(Locale.getDefault());
        String stringToSign = String.format("%s\n%s\n%s\n%s\n%s", method, contentMD5, contentType, date, requestPath);
        Key key = new SecretKeySpec(serverSecretKey.getBytes(), "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(key);
        byte[] signatureBytes = mac.doFinal(stringToSign.getBytes());
        return Base64.getEncoder().encodeToString(signatureBytes);
    }
}
