package org.nhathm.controller;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.APIConstant;
import org.nhathm.api.AuthService;
import org.nhathm.dto.clientobject.AuthLoginCO;
import org.nhathm.dto.command.AuthRegisterCmd;
import org.nhathm.dto.query.AuthLoginQry;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RequestMapping(APIConstant.WEB_API_PATH + "/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public Response register(@Valid @RequestBody AuthRegisterCmd cmd) {
        return authService.register(cmd);
    }

    @PostMapping("/login")
    public SingleResponse<AuthLoginCO> login(@RequestBody AuthLoginQry qry) {
        return authService.login(qry);
    }

    @PostMapping("/logout")
    public Response logout() {
        return authService.logout();
    }
}
