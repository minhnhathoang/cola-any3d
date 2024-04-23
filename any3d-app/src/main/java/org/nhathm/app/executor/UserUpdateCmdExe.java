package org.nhathm.app.executor;

import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import org.nhathm.domain.user.gateway.UserGateway;
import org.nhathm.dto.command.UserProfileUpdateCmd;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class UserUpdateCmdExe {

    private final UserGateway userGateway;

    public Response execute(UserProfileUpdateCmd cmd) {
        return null;
    }
}
