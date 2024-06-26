package org.nhathm.app.serviceimpl;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.api.AuthService;
import org.nhathm.app.executor.AuthLoginQryExe;
import org.nhathm.app.executor.AuthLogoutCmdExe;
import org.nhathm.app.executor.AuthRegisterCmdExe;
import org.nhathm.dto.clientobject.AuthLoginCO;
import org.nhathm.dto.command.AuthLogoutCmd;
import org.nhathm.dto.command.AuthRegisterCmd;
import org.nhathm.dto.query.AuthLoginQry;
import org.springframework.stereotype.Service;


@CatchAndLog
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthLoginQryExe authLoginQryExe;

    private final AuthRegisterCmdExe authRegisterCmdExe;

    private final AuthLogoutCmdExe authLogoutCmdExe;

    @Override
    public SingleResponse<AuthLoginCO> login(AuthLoginQry qry) {
        return authLoginQryExe.execute(qry);
    }

    @Override
    public Response register(AuthRegisterCmd cmd) {
        return authRegisterCmdExe.execute(cmd);
    }

    @Override
    public Response logout() {
        return authLogoutCmdExe.execute(new AuthLogoutCmd());
    }
}
