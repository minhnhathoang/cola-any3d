package org.nhathm.app.userprofile.executor.command;

import com.alibaba.cola.domain.DomainFactory;
import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import org.nhathm.domain.userprofile.entity.UserProfile;
import org.nhathm.domain.userprofile.gateway.UserProfileGateway;
import org.nhathm.user.dto.command.UserProfileUpdateCmd;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Component
public class UserProfileUpdateCmdExe {

    private final UserProfileGateway userProfileGateway;

    public Response execute(UserProfileUpdateCmd cmd) {
        UserProfile userProfile = DomainFactory.create(UserProfile.class);
        BeanUtils.copyProperties(cmd, UserProfile.class);
        userProfileGateway.updateUserProfile(userProfile);
        return Response.buildSuccess();
    }
}
