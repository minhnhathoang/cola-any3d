package org.nhathm.app.executor;

import com.alibaba.cola.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.dto.clientobject.UserCO;
import org.nhathm.dto.query.UserListByPageQry;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class UserListByPageQryExe {

//    private final UserRepository userRepository;

    public PageResponse<UserCO> execute(UserListByPageQry qry) {
        // TODO:
        return null;
    }
}
