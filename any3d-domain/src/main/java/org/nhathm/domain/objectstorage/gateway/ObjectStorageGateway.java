package org.nhathm.domain.objectstorage.gateway;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author nhathm
 */
public interface ObjectStorageGateway {

    boolean isBucketExist(String bucketName);

    void createBucket(String bucketName);

    void deleteBucket(String bucketName);

    String getPresignedPutUrl(String bucketName, String objectName, Map<String, String> requestParams);

    String getPresignedGetUrl(String bucketName, String objectName);

    String uploadObject(String bucketName, String objectName, String filePath);

    void uploadMultiPartObject(String bucketName, String objectName, MultipartFile file);
}
