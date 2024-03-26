package org.nhathm.app.object.service;

import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.BizException;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.nhathm.config.MinioConfig;
import org.nhathm.object.api.ObjectService;
import org.nhathm.object.dto.clientobject.PresignedUrlCO;
import org.nhathm.object.dto.query.ObjectGetPresignedUrlQry;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Service
public class ObjectServiceImpl implements ObjectService {

    private final MinioClient minioClient;

    @Override
    public SingleResponse<PresignedUrlCO> getPresignedUrl(ObjectGetPresignedUrlQry qry) {
        try {
            String url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.valueOf(qry.getMethod()))
                            .bucket(MinioConfig.COMMON_BUCKET_NAME)
                            .object(qry.getFileName())
                            .expiry(MinioConfig.PRESIGNED_URL_EXPIRY)
                            .build()
            );
            return SingleResponse.of(PresignedUrlCO.builder()
                    .url(url)
                    .expiry(MinioConfig.PRESIGNED_URL_EXPIRY)
                    .build());
        } catch (Exception e) {
            throw new BizException("object.getPresignedUrl.failed", e);
        }
    }
}
