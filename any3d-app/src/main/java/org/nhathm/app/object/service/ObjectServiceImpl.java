package org.nhathm.app.object.service;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.app.object.executor.command.UserDeleteCmdExe;
import org.nhathm.app.object.executor.command.UserUpdateCmdExe;
import org.nhathm.app.object.executor.query.UserByIdQryExe;
import org.nhathm.app.object.executor.query.UserCurrentExe;
import org.nhathm.app.object.executor.query.UserListByPageQryExe;
import org.nhathm.user.api.UserService;
import org.nhathm.user.dto.clientobject.UserCO;
import org.nhathm.user.dto.command.UserDeleteCmd;
import org.nhathm.user.dto.command.UserUpdateCmd;
import org.nhathm.user.dto.query.UserByIdQry;
import org.nhathm.user.dto.query.UserListByPageQry;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Service
public class ObjectServiceImpl implements UserService {

    private final UserUpdateCmdExe userUpdateCmdExe;

    private final UserDeleteCmdExe userDeleteCmdExe;

    private final UserCurrentExe userCurrentExe;

    private final UserByIdQryExe userByIdQryExe;

    private final UserListByPageQryExe userListByPageQryExe;

    @Override
    public Response updateUser(UserUpdateCmd cmd) {
        return userUpdateCmdExe.execute(cmd);
    }

    @Override
    public Response deleteUser(UserDeleteCmd cmd) {
        return userDeleteCmdExe.execute(cmd);
    }

    @Override
    public SingleResponse<UserCO> getCurrentUser() {
        return userCurrentExe.execute();
    }

    @Override
    public SingleResponse<UserCO> getUserBy(UserByIdQry qry) {
        return userByIdQryExe.execute(qry);
    }

    @Override
    public PageResponse<UserCO> listUserBy(UserListByPageQry qry) {
        return userListByPageQryExe.execute(qry);
    }
}
