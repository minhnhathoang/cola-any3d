package org.nhathm.app.userprofile.service;

import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import org.nhathm.user.api.UserProfileService;
import org.nhathm.user.dto.command.UserProfileUpdateCmd;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Service
public class UserProfileServiceImpl implements UserProfileService {


    @Override
    public Response updateUserProfile(UserProfileUpdateCmd cmd) {
        return null;
    }
}
