package org.nhathm.app.serviceimpl;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.api.UserProfileService;
import org.nhathm.app.executor.UserProfileGetCurrentExe;
import org.nhathm.app.executor.UserProfileUpdateCmdExe;
import org.nhathm.dto.clientobject.UserProfileCO;
import org.nhathm.dto.command.UserProfileUpdateCmd;
import org.springframework.stereotype.Service;


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
