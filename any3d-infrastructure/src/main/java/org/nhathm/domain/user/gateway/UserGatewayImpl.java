package org.nhathm.domain.user.gateway;

import lombok.RequiredArgsConstructor;
import org.nhathm.domain.user.database.UserConvertor;
import org.nhathm.domain.user.entity.User;
import org.nhathm.domain.user.entity.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Component
public class UserGatewayImpl implements UserGateway {

//    private final UserRepository userRepository;

    private final UserConvertor userConvertor;

    private final PasswordEncoder passwordEncoder;

    /**
     * for Security
     *
     * @param username alias email
     * @return User
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
//        return userConvertor.toUserDetails(
//                userRepository.findById(username).orElseThrow(() -> {
//                    throw new UsernameNotFoundException("User not found");
//                })
//        );
    }

    @Override
    public boolean existsByUsername(String username) {
        return false;
//        return userRepository.existsById(username);
    }

    @Override
    public void create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(userConvertor.toDataObject(user));
    }

    @Override
    public void update(User user) {
//        userRepository.save(userConvertor.toDataObject(user));
    }
}
