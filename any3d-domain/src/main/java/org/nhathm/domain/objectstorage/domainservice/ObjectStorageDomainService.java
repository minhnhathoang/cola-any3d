package org.nhathm.domain.objectstorage.domainservice;

import org.nhathm.domain.objectstorage.MetadataKey;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nhathm
 */
public class ObjectStorageDomainService {

    public static Map<String, String> formatToAMZMetaKey(Map<String, String> metadata) {
        if (metadata == null) {
            return null;
        }
        Map<String, String> newMetadata = new HashMap<>();
        metadata.forEach((key, value) -> {
            String newKey = toAMZMetaKey(key);
            newMetadata.put(newKey, value);
        });
        return newMetadata;
    }

    public static String toAMZMetaKey(String key) {
        return MetadataKey.X_AMZ_META_PREFIX + toTrainCase(key);
    }

    protected static String toTrainCase(String key) {
        return key.replaceAll("([a-z])([A-Z]+)", "$1-$2").toLowerCase();
    }
}
