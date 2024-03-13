package org.nhathm.app.user.executor.query;

import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.user.database.UserMapper;
import org.nhathm.user.dto.clientobject.UserCO;
import org.nhathm.user.dto.query.UserByIdQry;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Component
public class UserByIdQryExe {

    private final UserMapper userMapper;

    public SingleResponse<UserCO> execute(UserByIdQry qry) {
        // TODO:
        return null;
    }
}
