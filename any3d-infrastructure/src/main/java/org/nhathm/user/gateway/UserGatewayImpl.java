package org.nhathm.user.gateway;

import lombok.RequiredArgsConstructor;
import org.nhathm.domain.user.entity.User;
import org.nhathm.domain.user.entity.UserDetails;
import org.nhathm.domain.user.gateway.UserGateway;
import org.nhathm.user.database.UserConvertor;
import org.nhathm.user.database.UserMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Component
public class UserGatewayImpl implements UserGateway {

    private final UserMapper userMapper;

    private final UserConvertor userConvertor;

    /**
     * for Security
     *
     * @param username alias email
     * @return User
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public void save(User user) {
        userMapper.insert(userConvertor.toDataObject(user));
    }

    @Override
    public void updateById(User user) {
        userMapper.updateById(userConvertor.toDataObject(user));
    }

    @Override
    public void deleteById(User user) {
        userMapper.deleteById(user.getId());
    }
}
