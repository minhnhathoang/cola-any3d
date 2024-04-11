package org.nhathm.domain.userprofile.gateway;

import org.nhathm.domain.userprofile.entity.UserProfile;
import org.springframework.web.multipart.MultipartFile;

public interface UserProfileGateway {

    void updateUserProfile(UserProfile userProfile);

    UserProfile findByUserId(Long userId);

    void updateAvatar(Long userId, MultipartFile avatarFile);
}
