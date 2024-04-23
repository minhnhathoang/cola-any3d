package org.nhathm.app.auth.executor.command;

import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import org.nhathm.dto.command.AuthLogoutCmd;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class AuthLogoutCmdExe {

    public Response execute(AuthLogoutCmd cmd) {
        return Response.buildSuccess();
    }
}
