package org.nhathm.app.executor;

import com.alibaba.cola.dto.SingleResponse;
import io.minio.StatObjectResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import netscape.javascript.JSObject;
import org.nhathm.app.assembler.ContentAssembler;
import org.nhathm.config.MinioConfig;
import org.nhathm.domain.content.entity.Content;
import org.nhathm.domain.content.gateway.ContentGateway;
import org.nhathm.domain.project.database.ProjectMapper;
import org.nhathm.domain.userprofile.domainservice.VuforiaApiService;
import org.nhathm.domain.vuforia.database.VuforiaKeyConverter;
import org.nhathm.domain.vuforia.database.VuforiaKeyMapper;
import org.nhathm.domain.vuforia.dataobject.VuforiaKeyDO;
import org.nhathm.domain.vuforia.entity.VuforiaKey;
import org.nhathm.dto.clientobject.ContentCO;
import org.nhathm.dto.clientobject.ImageTargetCO;
import org.nhathm.dto.clientobject.VuforiaKeyCO;
import org.nhathm.dto.query.ContentGetByIdQry;
import org.nhathm.objectstorage.ObjectStorageService;
import org.springframework.stereotype.Component;
import util.json.JsonUtils;

@Slf4j
@RequiredArgsConstructor
@Component
public class ContentGetByIdQryExe {

    private final ContentGateway contentGateway;

    private final ContentAssembler contentAssembler;

    private final ObjectStorageService objectStorageService;

    private final VuforiaApiService vuforiaApiService;

    private final ProjectMapper projectMapper;

    private final VuforiaKeyMapper vuforiaKeyMapper;

    private final VuforiaKeyConverter vuforiaKeyConverter;


    public SingleResponse<ContentCO> execute(ContentGetByIdQry qry) {
        Content content = contentGateway.getById(qry.getId());
        ContentCO contentCO = contentAssembler.toCO(content);

        ImageTargetCO imageTargetCO = contentCO.getImageTarget();
        if (imageTargetCO != null) {
            String presignedUrl = objectStorageService.getPresignedGetUrl(MinioConfig.COMMON_BUCKET_NAME, imageTargetCO.getId());
            StatObjectResponse imageTargetObject = objectStorageService.getObjectInfo(MinioConfig.COMMON_BUCKET_NAME, imageTargetCO.getId());
            imageTargetCO.setUrl(presignedUrl);
            imageTargetCO.setSize(imageTargetObject.size());
            imageTargetCO.setContentType(imageTargetObject.contentType());

            var projectDO= projectMapper.selectById(content.getProjectId());
            VuforiaKeyDO keyDO = vuforiaKeyMapper.selectByProjectId(projectDO.getId());
            var key = vuforiaKeyConverter.toEntity(keyDO);
            imageTargetCO.setAdditionalData(JsonUtils.toJson(vuforiaApiService.getTarget(key, imageTargetCO.getId())));
            contentCO.setImageTarget(imageTargetCO);
        }
        return SingleResponse.of(contentCO);
    }
}
