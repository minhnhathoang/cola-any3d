package org.nhathm.app.vuforia.service;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.nhathm.api.VuforiaService;
import org.nhathm.app.vuforia.executor.command.VuforiaAddKeyCmdExe;
import org.nhathm.app.vuforia.executor.command.VuforiaUpdateKeyCmdExe;
import org.nhathm.app.vuforia.executor.query.VuforiaFindKeyQryExe;
import org.nhathm.dto.clientobject.VuforiaKeyCO;
import org.nhathm.dto.command.VuforiaAddKeyCmd;
import org.nhathm.dto.command.VuforiaUpdateKeyCmd;
import org.nhathm.dto.query.VuforiaFindKeyQry;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VuforiaServiceImpl implements VuforiaService {

    VuforiaAddKeyCmdExe vuforiaAddKeyCmdExe;

    VuforiaUpdateKeyCmdExe vuforiaUpdateKeyCmdExe;

    VuforiaFindKeyQryExe vuforiaFindKeyQryExe;

    @Override
    public Response addKey(VuforiaAddKeyCmd cmd) {
        return vuforiaAddKeyCmdExe.execute(cmd);
    }

    @Override
    public Response updateKey(VuforiaUpdateKeyCmd cmd) {
        return vuforiaUpdateKeyCmdExe.execute(cmd);
    }

    @Override
    public SingleResponse<VuforiaKeyCO> findKey(VuforiaFindKeyQry qry) {
        return vuforiaFindKeyQryExe.execute(qry);
    }
}
