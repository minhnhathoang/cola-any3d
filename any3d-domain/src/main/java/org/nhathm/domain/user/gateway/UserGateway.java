package org.nhathm.domain.user.gateway;

import org.nhathm.domain.user.entity.User;

public interface UserGateway {

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
