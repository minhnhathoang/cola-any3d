package org.nhathm.user.web;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.nhathm.common.APIConstant;
import org.nhathm.user.api.UserService;
import org.nhathm.user.dto.clientobject.UserCO;
import org.nhathm.user.dto.command.UserAddCmd;
import org.nhathm.user.dto.command.UserDeleteCmd;
import org.nhathm.user.dto.command.UserUpdateCmd;
import org.nhathm.user.dto.query.UserByIdQry;
import org.nhathm.user.dto.query.UserListByPageQry;
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

    @PostMapping
    public Response addUser(@Valid @RequestBody UserAddCmd cmd) {
        return userService.addUser(cmd);
    }

    @PutMapping("/{id}")
    public Response modifyUser(@PathVariable String id, @Valid @RequestBody UserUpdateCmd cmd) {
        cmd.setUserId(id);
        return userService.updateUser(cmd);
    }

    @DeleteMapping("/{id}")
    public Response deleteUser(@PathVariable String id) {
        return userService.deleteUser(UserDeleteCmd.builder().userId(id).build());
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
