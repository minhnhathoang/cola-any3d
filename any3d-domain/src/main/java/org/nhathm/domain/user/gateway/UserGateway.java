package org.nhathm.domain.user.gateway;

import org.nhathm.domain.user.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserGateway extends UserDetailsService {

    /**
     * Create a new user
     *
     * @param user
     */
    void save(User user);

    /**
     * @param user
     */
    void updateById(User user);

    /**
     * @param user
     */
    void deleteById(User user);
}
