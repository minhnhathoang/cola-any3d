package org.nhathm.user.gateway.impl;

import lombok.RequiredArgsConstructor;
import org.nhathm.domain.user.entity.User;
import org.nhathm.domain.user.gateway.UserGateway;
import org.nhathm.user.database.UserConvertor;
import org.nhathm.user.database.UserMapper;
import org.nhathm.user.dataobject.UserDO;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Repository
public class UserGatewayImpl implements UserGateway {

    private final UserMapper userMapper;

    private final UserConvertor userConvertor;

    @Override
    public User getById(String userId) {
        UserDO userDO = userMapper.selectById(userId);
        // TODO: Convert UserDO to User
        return null;
    }

    @Override
    public void save(User user) {
        UserDO userDO = userConvertor.toDataObject(user);
        userMapper.insert(userDO);
    }

    @Override
    public void updateById(User user) {
        UserDO userDO = userConvertor.toDataObject(user);
        userMapper.updateById(userDO);
    }

    @Override
    public void deleteById(User user) {
        userMapper.deleteById(user.getId());
    }
}
