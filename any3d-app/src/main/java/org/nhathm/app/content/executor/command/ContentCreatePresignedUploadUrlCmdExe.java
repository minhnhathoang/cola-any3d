package org.nhathm.app.content.executor.command;

import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhathm.common.SpringSecurityUtils;
import org.nhathm.config.MinioConfig;
import org.nhathm.domain.objectstorage.entity.MetadataKey;
import org.nhathm.domain.objectstorage.gateway.ObjectStorageGateway;
import org.nhathm.dto.clientobject.ContentCreatePresignedUploadUrlCO;
import org.nhathm.dto.command.ContentCreatePresignedUrlUploadHologramCmd;
import org.springframework.stereotype.Component;
import util.Strings;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
@Component
public class ContentCreatePresignedUploadUrlCmdExe {

    private final ObjectStorageGateway objectStorageGateway;

    public SingleResponse<ContentCreatePresignedUploadUrlCO> execute(ContentCreatePresignedUrlUploadHologramCmd cmd) {
        Map<String, String> requestParams = new HashMap<>();

        requestParams.put(MetadataKey.X_AMZ_META_IS_PRESIGNED_URL, Strings.EMPTY);
        requestParams.put(MetadataKey.X_AMZ_META_USER_ID, SpringSecurityUtils.getUserId());
        requestParams.put(MetadataKey.X_AMZ_META_PROJECT_ID, cmd.getProjectId());
        requestParams.put(MetadataKey.X_AMZ_META_FILE_NAME, cmd.getHologramFileName());

        String contentId = UUID.randomUUID().toString();
        String presignedUrl = objectStorageGateway.getPresignedPutUrl(MinioConfig.COMMON_BUCKET_NAME, contentId, requestParams);

        ContentCreatePresignedUploadUrlCO data = new ContentCreatePresignedUploadUrlCO();
        data.setContentId(contentId);
        data.setPresignedUrl(presignedUrl);
        return SingleResponse.of(data);
    }
}
