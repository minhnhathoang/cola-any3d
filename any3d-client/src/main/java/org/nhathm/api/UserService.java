package org.nhathm.api;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import org.nhathm.dto.clientobject.UserCO;
import org.nhathm.dto.command.UserChangePasswordCmd;
import org.nhathm.dto.command.UserProfileUpdateCmd;
import org.nhathm.dto.query.UserByIdQry;
import org.nhathm.dto.query.UserListByPageQry;


public interface UserService {

    SingleResponse<UserCO> getCurrentUser();

    Response updateUser(UserProfileUpdateCmd cmd);

    SingleResponse<UserCO> getUserBy(UserByIdQry qry);

    PageResponse<UserCO> listUserBy(UserListByPageQry cmd);

    Response changePassword(UserChangePasswordCmd cmd);
}
