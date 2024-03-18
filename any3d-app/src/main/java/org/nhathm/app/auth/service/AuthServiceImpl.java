package org.nhathm.app.auth.service;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.app.auth.executor.command.AuthRegisterCmdExe;
import org.nhathm.app.auth.executor.query.AuthLoginQryExe;
import org.nhathm.auth.api.AuthService;
import org.nhathm.auth.dto.AuthLoginCO;
import org.nhathm.auth.dto.command.AuthRegisterCmd;
import org.nhathm.auth.dto.query.AuthLoginQry;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@CatchAndLog
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthLoginQryExe authLoginQryExe;

    private final AuthRegisterCmdExe authRegisterCmdExe;

    @Override
    public SingleResponse<AuthLoginCO> login(AuthLoginQry qry) {
        return authLoginQryExe.execute(qry);
    }

    @Override
    public Response register(AuthRegisterCmd cmd) {
        return authRegisterCmdExe.execute(cmd);
    }
}
