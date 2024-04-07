package org.nhathm.domain.user.gateway;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhathm.domain.user.database.UserConvertor;
import org.nhathm.domain.user.database.UserMapper;
import org.nhathm.domain.user.dataobject.UserDO;
import org.nhathm.domain.user.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class UserGatewayImpl
        extends ServiceImpl<UserMapper, UserDO> implements UserGateway {

    private final UserConvertor userConvertor;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername: {}", username);
        var optionalUserDO = this.baseMapper.selectByUsername(username);
        if (optionalUserDO.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return userConvertor.toEntity(optionalUserDO.get());
    }

    @Override
    public boolean existsByUsername(String username) {
        return this.baseMapper.selectByUsername(username).isPresent();
    }


    @Override
    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.save(userConvertor.toDataObject(user));
    }

    @Override
    public void update(User user) {
//        userRepository.save(userConvertor.toDataObject(user));
    }
}
