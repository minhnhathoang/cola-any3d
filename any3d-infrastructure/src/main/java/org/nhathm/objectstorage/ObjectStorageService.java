package org.nhathm.objectstorage;

import com.alibaba.cola.exception.SysException;
import io.minio.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhathm.config.MinioConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author nhathm
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class ObjectStorageService {

    private final MinioClient minioClient;

    public boolean isBucketExist(String bucketName) {
        try {
            BucketExistsArgs args = BucketExistsArgs.builder().bucket(bucketName).build();
            return minioClient.bucketExists(args);
        } catch (Exception e) {
            throw new SysException("Error when checking bucket exist", e);
        }
    }

    public void createBucket(String bucketName) {
        try {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(MinioConfig.COMMON_BUCKET_NAME).build());
        } catch (Exception e) {
            throw new SysException("Error when creating bucket", e);
        }
    }

    public void deleteBucket(String bucketName) {
        try {
            minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            throw new SysException("Error when deleting bucket", e);
        }
    }

    public GetObjectResponse getObject(String bucketName, String objectName) {
        try {
            GetObjectArgs args = GetObjectArgs.builder().bucket(bucketName).object(objectName).build();
            return minioClient.getObject(args);
        } catch (Exception e) {
            log.error("Error when getting object {} {}", bucketName, objectName, e);
            throw new SysException("Error when getting object", e);
        }
    }

    public StatObjectResponse getObjectInfo(String bucketName, String objectName) {
        try {
            StatObjectArgs args = StatObjectArgs.builder().bucket(bucketName).object(objectName).build();
            return minioClient.statObject(args);
        } catch (Exception e) {
            log.error("Error when getting object info {} {}", bucketName, objectName, e);
            throw new SysException("Error when getting object info", e);
        }
    }

    public String getPresignedPutUrl(String bucketName, String objectName, Map<String, String> requestParams) {
        try {
            GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                    .method(Method.PUT)
                    .bucket(bucketName)
                    .object(objectName)
                    .extraQueryParams(requestParams)
                    .expiry(MinioConfig.PRESIGNED_URL_EXPIRY)
                    .build();
            return minioClient.getPresignedObjectUrl(args);
        } catch (Exception e) {
            throw new SysException("Error when getting pre-signed put url", e);
        }
    }

    public String getPresignedGetUrl(String bucketName, String objectName) {
        try {
            GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(bucketName)
                    .object(objectName)
                    .expiry(MinioConfig.PRESIGNED_URL_EXPIRY)
                    .build();
            return minioClient.getPresignedObjectUrl(args);
        } catch (Exception e) {
            throw new SysException("Error when getting pre-signed get url", e);
        }
    }

    public void uploadMultiPartObject(String bucketName, String objectName, MultipartFile file) {
        try {
            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build();
            minioClient.putObject(args);
        } catch (Exception e) {
            throw new SysException("Error when uploading multi-part object", e);
        }
    }
}
