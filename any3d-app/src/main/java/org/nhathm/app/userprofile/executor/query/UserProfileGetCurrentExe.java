package org.nhathm.app.userprofile.executor.query;

import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.app.userprofile.assembler.UserProfileAssembler;
import org.nhathm.auth.gateway.SpringSecurityUtils;
import org.nhathm.domain.userprofile.entity.UserProfile;
import org.nhathm.domain.userprofile.gateway.UserProfileGateway;
import org.nhathm.user.dto.clientobject.UserProfileCO;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Component
public class UserProfileGetCurrentExe {

    private final UserProfileAssembler userProfileAssembler;

    private final UserProfileGateway userProfileGateway;

    public SingleResponse<UserProfileCO> execute() {
        Long userId = SpringSecurityUtils.getUserId();
        UserProfile userProfile = userProfileGateway.findByUserId(userId);
        return SingleResponse.of(userProfileAssembler.toCO(userProfile));
    }
}
