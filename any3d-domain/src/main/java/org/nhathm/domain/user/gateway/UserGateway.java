package org.nhathm.domain.user.gateway;

import org.nhathm.domain.user.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserGateway extends UserDetailsService {

    boolean existsByUsername(String username);

    void create(User user);

    void update(User user);
}
