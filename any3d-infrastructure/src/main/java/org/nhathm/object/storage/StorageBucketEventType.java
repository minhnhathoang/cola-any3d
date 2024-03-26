package org.nhathm.object.storage;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
public enum StorageBucketEventType {
    s3_ObjectCreated_CompleteMultipartUpload,
    s3_ObjectCreated_Copy,
    s3_ObjectCreated_DeleteTagging,
    s3_ObjectCreated_Post,
    s3_ObjectCreated_Put,
    s3_ObjectCreated_PutLegalHold,
    s3_ObjectCreated_PutRetention,
    s3_ObjectCreated_PutTagging,

    s3_ObjectAccessed_Head,
    s3_ObjectAccessed_Get,
    s3_ObjectAccessed_GetRetention,
    s3_ObjectAccessed_GetLegalHold,

    s3_ObjectRemoved_Delete,
    s3_ObjectRemoved_DeleteMarkerCreated,

    s3_Replication_OperationCompletedReplication,
    s3_Replication_OperationFailedReplication,
    s3_Replication_OperationMissedThreshold,
    s3_Replication_OperationNotTracked,
    s3_Replication_OperationReplicatedAfterThreshold,

    s3_ObjectTransition_Failed,
    s3_ObjectTransition_Complete,
    s3_ObjectRestore_Post,
    s3_ObjectRestore_Completed,

    s3_Scanner_ManyVersions,
    s3_Scanner_BigPrefix;

    public String toEventName() {
        return this.name().replace("_", ":");
    }

    public static StorageBucketEventType fromEventName(String eventName) {
        try {
            return StorageBucketEventType.valueOf(eventName.replace(":", "_"));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid bucket event name: " + eventName);
        }
    }
}
