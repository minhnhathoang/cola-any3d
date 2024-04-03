package org.nhathm.object.storage;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
public interface ObjectStorageService {

    boolean isBucketExist(String bucketName);

    boolean createBucket(String bucketName);

    boolean deleteBucket(String bucketName);

    String getPresignedPutUrl(String bucketName, String objectName);

    String getPresignedGetUrl(String bucketName, String objectName);
}
