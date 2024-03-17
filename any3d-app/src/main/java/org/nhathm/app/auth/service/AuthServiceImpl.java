package org.nhathm.app.auth.service;

import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.app.auth.executor.query.AuthLoginQryExe;
import org.nhathm.auth.api.AuthService;
import org.nhathm.auth.dto.AuthLoginCO;
import org.nhathm.auth.dto.query.AuthLoginQry;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthLoginQryExe authLoginCmdExe;

    @Override
    public SingleResponse<AuthLoginCO> login(AuthLoginQry qry) {
        return authLoginCmdExe.execute(qry);
    }
}
