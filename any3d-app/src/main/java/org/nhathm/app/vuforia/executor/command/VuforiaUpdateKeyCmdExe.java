package org.nhathm.app.vuforia.executor.command;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.domain.DomainFactory;
import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import org.nhathm.domain.vuforia.entity.VuforiaKey;
import org.nhathm.domain.vuforia.gateway.VuforiaGateway;
import org.nhathm.dto.command.VuforiaUpdateKeyCmd;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@CatchAndLog
public class VuforiaUpdateKeyCmdExe {

    private final VuforiaGateway vuforiaGateway;

    public Response execute(VuforiaUpdateKeyCmd cmd) {
        VuforiaKey key = DomainFactory.create(VuforiaKey.class);
        key.setProjectId(cmd.getProjectId());
        key.setAccessKey(cmd.getAccessKey());
        key.setSecretKey(cmd.getSecretKey());
        vuforiaGateway.updateKey(key);
        return Response.buildSuccess();
    }
}
