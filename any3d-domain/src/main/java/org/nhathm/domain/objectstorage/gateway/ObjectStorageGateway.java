package org.nhathm.domain.objectstorage.gateway;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author nhathm
 */
public interface ObjectStorageGateway {

    boolean isBucketExist(String bucketName);

    void createBucket(String bucketName);

    void deleteBucket(String bucketName);

    String getPresignedPutUrl(String bucketName, String objectName);

    String getPresignedGetUrl(String bucketName, String objectName);

    String uploadObject(String bucketName, String objectName, String filePath);

    void uploadMultiPartObject(String bucketName, String objectName, MultipartFile file);
}
