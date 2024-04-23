package org.nhathm.app.user.executor.query;

import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.SysException;
import lombok.RequiredArgsConstructor;
import org.nhathm.app.user.assembler.UserAssembler;
import org.nhathm.common.SpringSecurityUtils;
import org.nhathm.domain.user.entity.User;
import org.nhathm.dto.clientobject.UserCO;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class UserGetCurrentExe {

    private final UserAssembler userAssembler;

    public SingleResponse<UserCO> execute() {
        var optionalUser = SpringSecurityUtils.getPrincipal();
        if (optionalUser.isEmpty()) {
            throw new SysException("Authentication required");
        }
        User user = optionalUser.get();
        UserCO userCO = userAssembler.toCO(user);
        return SingleResponse.of(userCO);
    }
}
