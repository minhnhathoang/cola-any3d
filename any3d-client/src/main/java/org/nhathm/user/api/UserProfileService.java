package org.nhathm.user.api;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import org.nhathm.user.dto.clientobject.UserProfileCO;
import org.nhathm.user.dto.command.UserProfileUpdateCmd;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
public interface UserProfileService {

    SingleResponse<UserProfileCO> getCurrentUserProfile();

    SingleResponse<UserProfileCO> getUserProfile(String userId);

    Response updateUserProfile(UserProfileUpdateCmd cmd);
}
