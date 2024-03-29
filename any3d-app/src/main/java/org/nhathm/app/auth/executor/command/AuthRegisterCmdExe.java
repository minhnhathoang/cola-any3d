package org.nhathm.app.auth.executor.command;

import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import org.nhathm.app.auth.assembler.UserAssembler;
import org.nhathm.auth.dto.command.AuthRegisterCmd;
import org.nhathm.domain.user.entity.User;
import org.nhathm.domain.user.gateway.UserGateway;
import org.nhathm.dto.data.ErrorCode;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Component
public class AuthRegisterCmdExe {

    private final UserGateway userGateway;

    private final UserAssembler userAssembler;

    public Response execute(AuthRegisterCmd cmd) {
        User user = userAssembler.toEntity(cmd);
        if (userGateway.existsByUsername(user.getUsername())) {
            throw ErrorCode.B_USER_UsernameAlreadyExist.toBizException();
        }
        userGateway.create(user);
        return Response.buildSuccess();
    }
}
