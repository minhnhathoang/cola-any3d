package org.nhathm.web;

import org.nhathm.common.APIConstant;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequestMapping(APIConstant.WEB_API_PATH + "/auth")
@RestController
public class LoginController {

    @PostMapping("/login")
    public String login() {
        return "login";
    }
}
