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

    @PutMapping("/{userId}")
    @PreAuthorize("@springSecurityUtils.isMe(#userId)")
    public Response updateUserProfile(@PathVariable String userId, @Valid @RequestBody UserProfileUpdateCmd cmd) {
        cmd.setUserId(userId);
        return userProfileService.updateUserProfile(cmd);
    }
}
