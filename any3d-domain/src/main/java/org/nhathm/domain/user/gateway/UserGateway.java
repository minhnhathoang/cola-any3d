package org.nhathm.domain.user.gateway;

import org.nhathm.domain.user.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserGateway extends UserDetailsService {

    User loadUserByUsername(String username);

    boolean existsByUsername(String username);

    void createUser(User user);

    void update(User user);
}
