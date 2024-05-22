package org.nhathm.domain.userprofile.gateway;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.nhathm.config.MinioConfig;
import org.nhathm.domain.userprofile.database.UserProfileConvertor;
import org.nhathm.domain.userprofile.database.UserProfileMapper;
import org.nhathm.domain.userprofile.dataobject.UserProfileDO;
import org.nhathm.domain.userprofile.entity.UserProfile;
import org.nhathm.objectstorage.ObjectStorageService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@RequiredArgsConstructor
@Component
public class UserProfileGatewayImpl
        extends ServiceImpl<UserProfileMapper, UserProfileDO> implements UserProfileGateway {

    private final UserProfileMapper userProfileMapper;

    private final UserProfileConvertor profileConvertor;

    private final ObjectStorageService objectStorageService;

    @Override
    public void updateUserProfile(UserProfile userProfile) {
        var userProfileDO =  userProfileMapper.selectByUserId(userProfile.getUserId());
        if (userProfileDO == null) {
            userProfileDO = profileConvertor.toDataObject(userProfile);
            userProfileMapper.insert(userProfileDO);
        } else {
            var newUserProfileDO = profileConvertor.toDataObject(userProfile);
            newUserProfileDO.setId(userProfileDO.getId());
            userProfileMapper.updateById(newUserProfileDO);
        }
    }

    @Override
    public UserProfile findByUserId(String userId) {
        return profileConvertor.toEntity(userProfileMapper.selectByUserId(userId));
    }

    @Override
    public void updateAvatar(String userId, MultipartFile avatarFile) {
        objectStorageService.uploadMultiPartObject(MinioConfig.PUBLIC_BUCKET_NAME, userId, avatarFile);
    }
}


