package org.nhathm.domain.objectstorage.gateway;

/**
 * @author nhathm
 */
public interface ObjectStorageGateway {

    boolean isBucketExist(String bucketName);

    void createBucket(String bucketName);

    void deleteBucket(String bucketName);

    String getPresignedPutUrl(String bucketName, String objectName);

    String getPresignedGetUrl(String bucketName, String objectName);
}
