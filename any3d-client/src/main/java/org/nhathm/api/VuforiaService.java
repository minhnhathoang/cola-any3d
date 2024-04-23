package org.nhathm.api;


import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import org.nhathm.dto.clientobject.VuforiaKeyCO;
import org.nhathm.dto.command.VuforiaAddKeyCmd;
import org.nhathm.dto.command.VuforiaUpdateKeyCmd;
import org.nhathm.dto.query.VuforiaFindKeyQry;

public interface VuforiaService {

    Response addKey(VuforiaAddKeyCmd cmd);

    Response updateKey(VuforiaUpdateKeyCmd cmd);

    SingleResponse<VuforiaKeyCO> findKey(VuforiaFindKeyQry qry);
}
