package org.nhathm.user.api;

import com.alibaba.cola.dto.Response;
import org.nhathm.user.dto.command.UserProfileUpdateCmd;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
public interface UserProfileService {

    Response updateUserProfile(UserProfileUpdateCmd cmd);

}
