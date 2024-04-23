package org.nhathm.app.vuforia.executor.query;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.domain.vuforia.entity.VuforiaKey;
import org.nhathm.domain.vuforia.gateway.VuforiaGateway;
import org.nhathm.dto.clientobject.VuforiaKeyCO;
import org.nhathm.dto.query.VuforiaFindKeyQry;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@CatchAndLog
public class VuforiaFindKeyQryExe {

    private final VuforiaGateway vuforiaGateway;

    public SingleResponse<VuforiaKeyCO> execute(VuforiaFindKeyQry qry) {
        VuforiaKey key = vuforiaGateway.findKeyByProjectId(qry.getProjectId());
        System.out.println("key: " + key);
        VuforiaKeyCO keyCO = new VuforiaKeyCO();
        BeanUtils.copyProperties(key, keyCO);
        System.out.println("keyCO: " + keyCO);
        return SingleResponse.of(keyCO);
    }
}
