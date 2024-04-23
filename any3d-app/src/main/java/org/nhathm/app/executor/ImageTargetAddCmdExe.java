package org.nhathm.app.executor;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.domain.DomainFactory;
import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.nhathm.domain.arsdk.ArSdkType;
import org.nhathm.domain.arsdk.VuforiaAddTargetResponse;
import org.nhathm.domain.content.entity.Content;
import org.nhathm.domain.content.gateway.ContentGateway;
import org.nhathm.domain.imagetarget.entity.ImageTarget;
import org.nhathm.domain.imagetarget.gateway.ImageTargetGateway;
import org.nhathm.domain.userprofile.domainservice.VuforiaApiService;
import org.nhathm.domain.vuforia.entity.VuforiaKey;
import org.nhathm.domain.vuforia.gateway.VuforiaGateway;
import org.nhathm.dto.command.ImageTargetAddCmd;
import org.springframework.stereotype.Component;
import util.json.JsonUtils;

import java.io.IOException;


@RequiredArgsConstructor
@Component
@CatchAndLog
@Log4j2
public class ImageTargetAddCmdExe {

    private final ContentGateway contentGateway;

    private final ImageTargetGateway imageTargetGateway;

    private final VuforiaGateway vuforiaGateway;

    private final VuforiaApiService vuforiaApiService;

    public Response execute(ImageTargetAddCmd cmd) throws IOException {
        ArSdkType type = ArSdkType.valueOf(cmd.getArSdkType());
        if (type != ArSdkType.VUFORIA) {
            return Response.buildFailure("Not implemented", "Only support Vuforia for now");
        }

        Content content = contentGateway.getById(cmd.getContentId());
        VuforiaKey key = vuforiaGateway.findKeyByProjectId(content.getProjectId());
        if (key == null) {
            return Response.buildFailure("Key not found", "Vuforia key not found for project");
        }

        VuforiaAddTargetResponse vuforiaResponse = vuforiaApiService.addTarget(key, cmd.getImageFile());
        if (!vuforiaResponse.isSuccess()) {
            log.error("Failed to upload image target to Vuforia cloud: {}", JsonUtils.toJson(vuforiaResponse));
            return Response.buildFailure(vuforiaResponse.getResultCode(), "Failed to upload image target to Vuforia cloud");
        }

        String targetId = vuforiaResponse.getTargetId();
        ImageTarget imageTarget = DomainFactory.create(ImageTarget.class);
        imageTarget.setId(targetId);
        imageTarget.setContentId(cmd.getContentId());
        imageTarget.setArSdkType(type);
        imageTarget.setFilename(cmd.getImageFile().getOriginalFilename());
        imageTargetGateway.addImageTarget(imageTarget);

        return Response.buildSuccess();
    }
}
