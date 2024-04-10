package org.nhathm.domain.userprofile.gateway;

import org.nhathm.domain.userprofile.entity.UserProfile;

public interface UserProfileGateway {

    void updateUserProfile(UserProfile userProfile);

    UserProfile findByUserId(Long userId);
}
