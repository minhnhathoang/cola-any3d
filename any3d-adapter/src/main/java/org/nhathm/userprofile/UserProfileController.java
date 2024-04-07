package org.nhathm.userprofile;

import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import org.nhathm.APIConstant;
import org.nhathm.user.api.UserProfileService;
import org.nhathm.user.dto.command.UserProfileUpdateCmd;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@RequestMapping(APIConstant.WEB_API_PATH + "/user-profiles")
@RestController
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PutMapping("/{id}")
    @PreAuthorize("#id == authenticationpo.principal.id")
    public Response updateUserProfile(@PathVariable String id, @Valid @RequestBody UserProfileUpdateCmd cmd) {
//        cmd.setUserId(id);
        return userProfileService.updateUserProfile(cmd);
    }

//    @DeleteMapping("/{id}")
//    public Response deleteUser(@PathVariable String id) {
//        return userService.deleteUser(UserDeleteCmd.builder().userId(id).build());
//    }
//
//    @GetMapping("/me/profile")
//    public SingleResponse<Object> getCurrentUser() {
//        return SingleResponse.of(SecurityContextHolder.getContext().getAuthentication());
//    }
//
//    @GetMapping("/{id}")
//    public SingleResponse<UserCO> getUserBy(@PathVariable String id) {
//        return userService.getUserBy(UserByIdQry.builder().userId(id).build());
//    }
//
//    @GetMapping
//    public PageResponse<UserCO> listUserBy(@Valid @ModelAttribute UserListByPageQry qry) {
//        return userService.listUserBy(qry);
//    }
}
