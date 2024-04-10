package org.nhathm.user;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.APIConstant;
import org.nhathm.user.api.UserService;
import org.nhathm.user.dto.clientobject.UserCO;
import org.nhathm.user.dto.command.UserDeleteCmd;
import org.nhathm.user.dto.command.UserProfileUpdateCmd;
import org.nhathm.user.dto.command.query.UserByIdQry;
import org.nhathm.user.dto.command.query.UserListByPageQry;
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
    public Response updateUser(@PathVariable String id, @Valid @RequestBody UserProfileUpdateCmd cmd) {
        cmd.setUserId(id);
        return userService.updateUser(cmd);
    }

    @DeleteMapping("/{id}")
    public Response deleteUser(@PathVariable String id) {
        return userService.deleteUser(UserDeleteCmd.builder().userId(id).build());
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
}
