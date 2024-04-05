package org.nhathm.auth.gateway;

import lombok.RequiredArgsConstructor;
import org.nhathm.domain.auth.gateway.AuthGateway;
import org.nhathm.domain.user.entity.User;
import org.nhathm.user.database.UserConvertor;
import org.nhathm.user.database.UserMapper;
import org.nhathm.user.dataobject.UserDO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Component
@RequiredArgsConstructor
public class AuthGatewayImpl implements AuthGateway {

//    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final UserConvertor userConvertor;

    @Override
    public User login(String username, String password) {
        UserDO userDO = userMapper.selectByUsername(username);
        boolean isMatch = passwordEncoder.matches(password, userDO.getHashedPassword());
        return isMatch ? userConvertor.toEntity(userDO) : null;
    }
}
