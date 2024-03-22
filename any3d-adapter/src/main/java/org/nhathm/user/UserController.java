package org.nhathm.user;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.constant.APIConstant;
import org.nhathm.user.api.UserService;
import org.nhathm.user.dto.clientobject.UserCO;
import org.nhathm.user.dto.command.UserDeleteCmd;
import org.nhathm.user.dto.command.UserUpdateCmd;
import org.nhathm.user.dto.query.UserByIdQry;
import org.nhathm.user.dto.query.UserListByPageQry;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@RequestMapping(APIConstant.WEB_API_PATH + "/users")
@RestController
public class UserController {

    private final UserService userService;

    @PutMapping("/{id}")
    public Response modifyUser(@PathVariable String id, @Valid @RequestBody UserUpdateCmd cmd) {
        cmd.setUserId(id);
        return userService.updateUser(cmd);
    }

    @DeleteMapping("/{id}")
    public Response deleteUser(@PathVariable String id) {
        return userService.deleteUser(UserDeleteCmd.builder().userId(id).build());
    }

    @GetMapping("/me/profile")
    public SingleResponse<Object> getCurrentUser() {
        return SingleResponse.of(SecurityContextHolder.getContext().getAuthentication());
    }

    @GetMapping("/{id}")
    public SingleResponse<UserCO> getUserBy(@PathVariable String id) {
        return userService.getUserBy(UserByIdQry.builder().userId(id).build());
    }

    @GetMapping
    public PageResponse<UserCO> listUserBy(@Valid @ModelAttribute UserListByPageQry qry) {
        return userService.listUserBy(qry);
    }
}
