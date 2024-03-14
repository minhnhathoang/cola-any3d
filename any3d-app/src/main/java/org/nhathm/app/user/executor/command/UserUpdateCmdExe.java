package org.nhathm.app.user.executor.command;

import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import org.nhathm.domain.user.gateway.UserGateway;
import org.nhathm.user.dto.command.UserUpdateCmd;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Component
public class UserUpdateCmdExe {

    private final UserGateway userGateway;

    public Response execute(UserUpdateCmd cmd) {
        return null;
    }
}
