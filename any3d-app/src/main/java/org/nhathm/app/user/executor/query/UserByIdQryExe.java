package org.nhathm.app.user.executor.query;

import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.app.auth.assembler.UserAssembler;
import org.nhathm.user.database.UserRepository;
import org.nhathm.user.dataobject.UserDO;
import org.nhathm.user.dto.clientobject.UserCO;
import org.nhathm.user.dto.command.query.UserByIdQry;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Component
public class UserByIdQryExe {

    private final UserRepository userRepository;

    private final UserAssembler userAssembler;

    public SingleResponse<UserCO> execute(UserByIdQry qry) {
        UserDO userDO = userRepository.findById(qry.getUserId()).orElse(null);
        return SingleResponse.of(userAssembler.toCO(userDO));
    }
}
