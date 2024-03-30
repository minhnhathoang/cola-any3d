package org.nhathm.auth;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.auth.api.AuthService;
import org.nhathm.auth.dto.AuthLoginCO;
import org.nhathm.auth.dto.command.AuthRegisterCmd;
import org.nhathm.auth.dto.query.AuthLoginQry;
import org.nhathm.constant.APIConstant;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@RequestMapping(APIConstant.WEB_API_PATH + "/auth")
@RestController
@CatchAndLog
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public SingleResponse<AuthLoginCO> login(@RequestBody AuthLoginQry qry) {
        return authService.login(qry);
    }

    @PostMapping("/register")
    public Response register(@Valid @RequestBody AuthRegisterCmd cmd) {
        return authService.register(cmd);
    }

    @PostMapping("/logout")
    public Response logout() {
        return authService.logout();
    }
}
