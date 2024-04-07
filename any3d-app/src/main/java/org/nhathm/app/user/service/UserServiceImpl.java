package org.nhathm.app.user.service;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.app.user.executor.command.UserDeleteCmdExe;
import org.nhathm.app.user.executor.command.UserUpdateCmdExe;
import org.nhathm.app.user.executor.query.UserCurrentExe;
import org.nhathm.app.user.executor.query.UserListByPageQryExe;
import org.nhathm.user.api.UserService;
import org.nhathm.user.dto.clientobject.UserCO;
import org.nhathm.user.dto.command.UserDeleteCmd;
import org.nhathm.user.dto.command.UserProfileUpdateCmd;
import org.nhathm.user.dto.command.query.UserByIdQry;
import org.nhathm.user.dto.command.query.UserListByPageQry;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserUpdateCmdExe userUpdateCmdExe;

    private final UserDeleteCmdExe userDeleteCmdExe;

    private final UserCurrentExe userCurrentExe;

    private final UserListByPageQryExe userListByPageQryExe;


    @Override
    public Response updateUser(UserProfileUpdateCmd cmd) {
        return null;
    }

    @Override
    public Response deleteUser(UserDeleteCmd cmd) {
        return null;
    }

    @Override
    public SingleResponse<UserCO> getCurrentUser() {
        return userCurrentExe.execute();
    }

    @Override
    public SingleResponse<UserCO> getUserBy(UserByIdQry qry) {
        return null;
    }


    @Override
    public PageResponse<UserCO> listUserBy(UserListByPageQry qry) {
        return userListByPageQryExe.execute(qry);
    }
}
