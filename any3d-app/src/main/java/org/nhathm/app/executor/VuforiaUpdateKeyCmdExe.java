package org.nhathm.app.executor;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.domain.DomainFactory;
import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import org.nhathm.domain.userprofile.domainservice.VuforiaApiService;
import org.nhathm.domain.vuforia.entity.VuforiaKey;
import org.nhathm.domain.vuforia.gateway.VuforiaGateway;
import org.nhathm.dto.command.VuforiaUpdateKeyCmd;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@CatchAndLog
public class VuforiaUpdateKeyCmdExe {

    private final VuforiaGateway vuforiaGateway;

    private final VuforiaApiService vuforiaApiService;

    public Response execute(VuforiaUpdateKeyCmd cmd) {
        VuforiaKey key = DomainFactory.create(VuforiaKey.class);
        key.setId(cmd.getId());
        key.setAccessKey(cmd.getAccessKey());
        key.setSecretKey(cmd.getSecretKey());

        if (!vuforiaApiService.isValidKey(key)) {
            return Response.buildFailure("ERROR", "Invalid Vuforia Server Access Keys");
        }

        vuforiaGateway.updateKey(key);
        return Response.buildSuccess();
    }
}
