package org.nhathm.app.executor;

import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.app.assembler.UserProfileAssembler;
import org.nhathm.common.SpringSecurityUtils;
import org.nhathm.domain.userprofile.entity.UserProfile;
import org.nhathm.domain.userprofile.gateway.UserProfileGateway;
import org.nhathm.dto.clientobject.UserProfileCO;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class UserProfileGetCurrentExe {

    private final UserProfileAssembler userProfileAssembler;

    private final UserProfileGateway userProfileGateway;

    public SingleResponse<UserProfileCO> execute() {
        String userId = SpringSecurityUtils.getUserId();
        UserProfile userProfile = userProfileGateway.findByUserId(userId);
        return SingleResponse.of(userProfileAssembler.toCO(userProfile));
    }
}
