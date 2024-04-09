package org.nhathm.app.project.executor.command;

import com.alibaba.cola.domain.DomainFactory;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.ErrorCode;
import org.nhathm.config.MinioConfig;
import org.nhathm.domain.objectstorage.gateway.ObjectStorageGateway;
import org.nhathm.domain.project.entity.Content;
import org.nhathm.domain.project.gateway.ContentGateway;
import org.nhathm.domain.project.gateway.ProjectGateway;
import org.nhathm.project.api.ContentCreatePresignedUploadUrlCO;
import org.nhathm.project.dto.command.ContentCreatePresignedUploadUrlCmd;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Component
public class ContentCreatePresignedUploadUrlCmdExe {

    private final ProjectGateway projectGateway;

    private final ContentGateway contentGateway;

    private final ObjectStorageGateway objectStorageGateway;

    public SingleResponse<ContentCreatePresignedUploadUrlCO> execute(ContentCreatePresignedUploadUrlCmd cmd) {
        if (!projectGateway.isExistsById(cmd.getProjectId())) {
            throw ErrorCode.B_PROJECT_ProjectNotFound.toBizException();
        }
        Content content = DomainFactory.create(Content.class);
        content.setProjectId(cmd.getProjectId());
        Long contentId = contentGateway.createContent(content);
        String preSignedUrl = objectStorageGateway.getPresignedPutUrl(MinioConfig.COMMON_BUCKET_NAME, contentId.toString());
        return SingleResponse.of(ContentCreatePresignedUploadUrlCO.builder()
                .contentId(contentId.toString())
                .presignedUrl(preSignedUrl)
                .build());
    }
}
