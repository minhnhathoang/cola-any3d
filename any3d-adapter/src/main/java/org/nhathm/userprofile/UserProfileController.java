package org.nhathm.userprofile;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.APIConstant;
import org.nhathm.api.UserProfileService;
import org.nhathm.common.SpringSecurityUtils;
import org.nhathm.dto.clientobject.UserProfileCO;
import org.nhathm.dto.command.UserProfileUpdateCmd;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RequestMapping(APIConstant.WEB_API_PATH + "/user-profiles")
@RestController
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/me")
    public SingleResponse<UserProfileCO> getCurrentUserProfile() {
        return userProfileService.getCurrentUserProfile();
    }

    @PutMapping(path = "/me", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Response updateUserProfileAvatar(@Valid @ModelAttribute UserProfileUpdateCmd cmd) {
        cmd.setUserId(SpringSecurityUtils.getUserId());
        return userProfileService.updateUserProfile(cmd);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("@springSecurityUtils.isMe(#userId)")
    public Response getUserProfileBy(@PathVariable String userId) {
        return userProfileService.getUserProfile(userId);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("@springSecurityUtils.isMe(#userId)")
    public Response updateUserProfile(@PathVariable String userId, @Valid @RequestBody UserProfileUpdateCmd cmd) {
        cmd.setUserId(userId);
        return userProfileService.updateUserProfile(cmd);
    }
}
