package org.nhathm.app.user.executor.query;

import com.alibaba.cola.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.user.dto.clientobject.UserCO;
import org.nhathm.user.dto.command.query.UserListByPageQry;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Component
public class UserListByPageQryExe {

//    private final UserRepository userRepository;

    public PageResponse<UserCO> execute(UserListByPageQry qry) {
        // TODO:
        return null;
    }
}
