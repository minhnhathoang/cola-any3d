package org.nhathm.domain.objectstorage.entity;

/**
 * @author nhathm
 */
public class MetadataKey {

    public static final String X_AMZ_META_PREFIX = "X-Amz-Meta-";

    public static final String X_AMZ_META_USER_ID = X_AMZ_META_PREFIX + "User-Id";
    public static final String X_AMZ_META_IS_PRESIGNED_URL = X_AMZ_META_PREFIX + "Is-Presigned-Url";
    public static final String X_AMZ_META_CONTENT_TYPE = X_AMZ_META_PREFIX + "Content-Type";
    public static final String X_AMZ_META_CONTENT_ID = X_AMZ_META_PREFIX + "Content-Id";
    public static final String X_AMZ_META_PROJECT_ID = X_AMZ_META_PREFIX + "Project-Id";
    public static final String X_AMZ_META_FILE_NAME = X_AMZ_META_PREFIX + "File-Name";
}
