package org.nhathm.app.auth.executor.command;

import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import org.nhathm.auth.dto.command.AuthLogoutCmd;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Component
public class AuthLogoutCmdExe {

    public Response execute(AuthLogoutCmd cmd) {
        return Response.buildSuccess();
    }
}
