package org.nhathm.auth.gateway;

import lombok.RequiredArgsConstructor;
import org.nhathm.domain.auth.gateway.AuthGateway;
import org.nhathm.domain.user.entity.User;
import org.nhathm.user.database.UserConvertor;
import org.nhathm.user.database.UserRepository;
import org.nhathm.user.dataobject.UserDO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Component
@RequiredArgsConstructor
public class AuthGatewayImpl implements AuthGateway {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserConvertor userConvertor;

    @Override
    public User login(String username, String password) {
        Optional<UserDO> userDO = userRepository.findById(username)
                .filter(DO -> passwordEncoder.matches(password, DO.getHashedPassword()));
        return userConvertor.toEntity(userDO.orElse(null));
    }
}
