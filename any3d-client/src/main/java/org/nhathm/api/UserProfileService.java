package org.nhathm.api;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import org.nhathm.dto.clientobject.UserProfileCO;
import org.nhathm.dto.command.UserProfileUpdateCmd;


public interface UserProfileService {

    SingleResponse<UserProfileCO> getCurrentUserProfile();

    SingleResponse<UserProfileCO> getUserProfile(String userId);

    Response updateUserProfile(UserProfileUpdateCmd cmd);
}
