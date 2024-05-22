package org.nhathm.controller;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.APIConstant;
import org.nhathm.api.UserService;
import org.nhathm.common.SpringSecurityUtils;
import org.nhathm.dto.clientobject.UserCO;
import org.nhathm.dto.command.AuthRegisterCmd;
import org.nhathm.dto.command.UserChangePasswordCmd;
import org.nhathm.dto.command.UserProfileUpdateCmd;
import org.nhathm.dto.query.UserByIdQry;
import org.nhathm.dto.query.UserListByPageQry;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RequestMapping(APIConstant.WEB_API_PATH + "/users")
@RestController
public class UserController {

    private final UserService userService;

    @PutMapping("/{id}")
    public Response updateUser(@PathVariable String id, @Valid @RequestBody UserProfileUpdateCmd cmd) {
        cmd.setUserId(id);
        return userService.updateUser(cmd);
    }

    @GetMapping("/me")
    public SingleResponse<UserCO> getCurrentUser() {
        return userService.getCurrentUser();
    }

    @GetMapping("/{id}")
    public SingleResponse<UserCO> getUserBy(@PathVariable String id) {
        return userService.getUserBy(UserByIdQry.builder().userId(id).build());
    }

    @GetMapping
    public PageResponse<UserCO> getUserListBy(@Valid @ModelAttribute UserListByPageQry qry) {
        return userService.listUserBy(qry);
    }

    @PutMapping("/change-password")
    public Response changePassword(@Valid @RequestBody UserChangePasswordCmd cmd) {
        cmd.setUserId(SpringSecurityUtils.getUserId());
        return userService.changePassword(cmd);
    }
}
