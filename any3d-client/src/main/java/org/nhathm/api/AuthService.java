package org.nhathm.api;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import org.nhathm.dto.clientobject.AuthLoginCO;
import org.nhathm.dto.command.AuthRegisterCmd;
import org.nhathm.dto.query.AuthLoginQry;


public interface AuthService {

    SingleResponse<AuthLoginCO> login(AuthLoginQry qry);

    Response register(AuthRegisterCmd cmd);

    Response logout();
}
