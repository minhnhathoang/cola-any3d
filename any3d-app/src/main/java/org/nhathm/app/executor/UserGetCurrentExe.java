package org.nhathm.app.executor;

import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.SysException;
import lombok.RequiredArgsConstructor;
import org.nhathm.app.assembler.UserAssembler;
import org.nhathm.common.SpringSecurityUtils;
import org.nhathm.domain.user.entity.User;
import org.nhathm.domain.user.gateway.UserGateway;
import org.nhathm.dto.clientobject.UserCO;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class UserGetCurrentExe {

    private final UserGateway userGateway;
    private final UserAssembler userAssembler;

    public SingleResponse<UserCO> execute() {
        String username = SpringSecurityUtils.getUserName()
                .orElseThrow(() -> new SysException("User not found"));
        User user = userGateway.loadUserByUsername(username);
        UserCO userCO = userAssembler.toCO(user);
        return SingleResponse.of(userCO);
    }
}
