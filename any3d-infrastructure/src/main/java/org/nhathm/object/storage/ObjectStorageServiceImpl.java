package org.nhathm.object.storage;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
public class ObjectStorageServiceImpl implements ObjectStorageService {
    @Override
    public boolean isBucketExist(String bucketName) {
        return false;
    }

    @Override
    public boolean createBucket(String bucketName) {
        return false;
    }

    @Override
    public boolean deleteBucket(String bucketName) {
        return false;
    }

    @Override
    public String getPresignedPutUrl(String bucketName, String objectName) {
        return null;
    }

    @Override
    public String getPresignedGetUrl(String bucketName, String objectName) {
        return null;
    }
}
