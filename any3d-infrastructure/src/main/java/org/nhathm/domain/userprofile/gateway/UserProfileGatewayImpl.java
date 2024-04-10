package org.nhathm.domain.userprofile.gateway;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.nhathm.domain.userprofile.database.UserProfileConvertor;
import org.nhathm.domain.userprofile.database.UserProfileMapper;
import org.nhathm.domain.userprofile.dataobject.UserProfileDO;
import org.nhathm.domain.userprofile.entity.UserProfile;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Component
public class UserProfileGatewayImpl
        extends ServiceImpl<UserProfileMapper, UserProfileDO> implements UserProfileGateway {

    private final UserProfileMapper userProfileMapper;

    private final UserProfileConvertor profileConvertor;

    @Override
    public void updateUserProfile(UserProfile userProfile) {
        this.saveOrUpdate(profileConvertor.toDataObject(userProfile));
    }

    @Override
    public UserProfile findByUserId(Long userId) {
        return profileConvertor.toEntity(userProfileMapper.selectById(userId));
    }
}


