package org.nhathm.app.serviceimpl;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.api.UserService;
import org.nhathm.app.executor.UserChangePasswordCmdExe;
import org.nhathm.app.executor.UserGetCurrentExe;
import org.nhathm.app.executor.UserListByPageQryExe;
import org.nhathm.app.executor.UserUpdateCmdExe;
import org.nhathm.dto.clientobject.UserCO;
import org.nhathm.dto.command.UserChangePasswordCmd;
import org.nhathm.dto.command.UserProfileUpdateCmd;
import org.nhathm.dto.query.UserByIdQry;
import org.nhathm.dto.query.UserListByPageQry;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserGetCurrentExe userGetCurrentExe;

    private final UserListByPageQryExe userListByPageQryExe;

    private final UserChangePasswordCmdExe userChangePasswordCmdExe;

    @Override
    public SingleResponse<UserCO> getCurrentUser() {
        return userGetCurrentExe.execute();
    }

    @Override
    public Response updateUser(UserProfileUpdateCmd cmd) {
        return null;
    }

    @Override
    public SingleResponse<UserCO> getUserBy(UserByIdQry qry) {
        return null;
    }


    @Override
    public PageResponse<UserCO> listUserBy(UserListByPageQry qry) {
        return userListByPageQryExe.execute(qry);
    }

    @Override
    public Response changePassword(UserChangePasswordCmd cmd) {
        return userChangePasswordCmdExe.execute(cmd);
    }
}
