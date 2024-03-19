package org.nhathm.user.api;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import org.nhathm.user.dto.clientobject.UserCO;
import org.nhathm.user.dto.command.UserDeleteCmd;
import org.nhathm.user.dto.command.UserUpdateCmd;
import org.nhathm.user.dto.query.UserByIdQry;
import org.nhathm.user.dto.query.UserListByPageQry;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
public interface UserService {

    Response updateUser(UserUpdateCmd cmd);

    Response deleteUser(UserDeleteCmd cmd);

    SingleResponse<UserCO> getCurrentUser();

    SingleResponse<UserCO> getUserBy(UserByIdQry qry);

    PageResponse<UserCO> listUserBy(UserListByPageQry cmd);
}
