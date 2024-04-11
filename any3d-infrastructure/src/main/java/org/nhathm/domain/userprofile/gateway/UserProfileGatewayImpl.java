package org.nhathm.domain.userprofile.gateway;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.nhathm.config.MinioConfig;
import org.nhathm.domain.objectstorage.gateway.ObjectStorageGateway;
import org.nhathm.domain.userprofile.database.UserProfileConvertor;
import org.nhathm.domain.userprofile.database.UserProfileMapper;
import org.nhathm.domain.userprofile.dataobject.UserProfileDO;
import org.nhathm.domain.userprofile.entity.UserProfile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Component
public class UserProfileGatewayImpl
        extends ServiceImpl<UserProfileMapper, UserProfileDO> implements UserProfileGateway {

    private final UserProfileMapper userProfileMapper;

    private final UserProfileConvertor profileConvertor;

    private final ObjectStorageGateway objectStorageGateway;

    @Override
    public void updateUserProfile(UserProfile userProfile) {
        this.saveOrUpdate(profileConvertor.toDataObject(userProfile));
    }

    @Override
    public UserProfile findByUserId(Long userId) {
        return profileConvertor.toEntity(userProfileMapper.selectById(userId));
    }

    @Override
    public void updateAvatar(Long userId, MultipartFile avatarFile) {
        objectStorageGateway.uploadMultiPartObject(MinioConfig.PUBLIC_BUCKET_NAME, userId.toString(), avatarFile);
    }
}


