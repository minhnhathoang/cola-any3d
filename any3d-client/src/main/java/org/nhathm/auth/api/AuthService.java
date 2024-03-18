package org.nhathm.auth.api;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import org.nhathm.auth.dto.AuthLoginCO;
import org.nhathm.auth.dto.command.AuthRegisterCmd;
import org.nhathm.auth.dto.query.AuthLoginQry;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
public interface AuthService {

    SingleResponse<AuthLoginCO> login(AuthLoginQry qry);

    Response register(AuthRegisterCmd cmd);
}
