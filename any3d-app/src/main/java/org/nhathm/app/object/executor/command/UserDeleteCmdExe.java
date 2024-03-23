package org.nhathm.app.object.executor.command;

import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import org.nhathm.domain.user.gateway.UserGateway;
import org.nhathm.user.dto.command.UserDeleteCmd;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Component
public class UserDeleteCmdExe {

    private final UserGateway userGateway;

    public Response execute(UserDeleteCmd cmd) {
        // TODO:
        return null;
    }
}
