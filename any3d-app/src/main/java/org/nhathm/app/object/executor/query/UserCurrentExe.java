package org.nhathm.app.object.executor.query;

import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.app.auth.assembler.UserAssembler;
import org.nhathm.user.database.UserRepository;
import org.nhathm.user.dto.clientobject.UserCO;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Component
public class UserCurrentExe {

    private final UserRepository userRepository;

    private final UserAssembler userAssembler;

    public SingleResponse<UserCO> execute() {
        return SingleResponse.buildSuccess();
    }
}
