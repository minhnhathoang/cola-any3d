package org.nhathm.app.userprofile.executor.command;

import com.alibaba.cola.domain.DomainFactory;
import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import org.nhathm.config.MinioConfig;
import org.nhathm.domain.userprofile.entity.UserProfile;
import org.nhathm.domain.userprofile.gateway.UserProfileGateway;
import org.nhathm.dto.command.UserProfileUpdateCmd;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class UserProfileUpdateCmdExe {

    private final UserProfileGateway userProfileGateway;

    private final MinioConfig minioConfig;

    public Response execute(UserProfileUpdateCmd cmd) {
        UserProfile userProfile = DomainFactory.create(UserProfile.class);
        BeanUtils.copyProperties(cmd, userProfile);
        if (cmd.getAvatarFile() != null) {
            userProfileGateway.updateAvatar(userProfile.getUserId(), cmd.getAvatarFile());
            userProfile.setAvatar(minioConfig.getPublicUrl(userProfile.getUserId().toString()));
        }
        userProfileGateway.updateUserProfile(userProfile);
        return Response.buildSuccess();
    }
}
