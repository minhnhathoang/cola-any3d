package org.nhathm.app.userprofile.service;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.app.userprofile.executor.command.UserProfileUpdateCmdExe;
import org.nhathm.app.userprofile.executor.query.UserProfileGetCurrentExe;
import org.nhathm.user.api.UserProfileService;
import org.nhathm.user.dto.clientobject.UserProfileCO;
import org.nhathm.user.dto.command.UserProfileUpdateCmd;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileUpdateCmdExe userProfileUpdateCmdExe;

    private final UserProfileGetCurrentExe userProfileGetCurrentExe;

    @Override
    public SingleResponse<UserProfileCO> getCurrentUserProfile() {
        return userProfileGetCurrentExe.execute();
    }

    @Override
    public SingleResponse<UserProfileCO> getUserProfile(String userId) {
        return null;
    }

    @Override
    public Response updateUserProfile(UserProfileUpdateCmd cmd) {
        return userProfileUpdateCmdExe.execute(cmd);
    }
}
