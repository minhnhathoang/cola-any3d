package org.nhathm.user.gateway;

import lombok.RequiredArgsConstructor;
import org.nhathm.domain.user.entity.User;
import org.nhathm.domain.user.gateway.UserGateway;
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

    @Override
    public User getById(String userId) {
        UserDO userDO = userMapper.selectById(userId);
        // TODO: Convert UserDO to User
        return null;
    }
}
