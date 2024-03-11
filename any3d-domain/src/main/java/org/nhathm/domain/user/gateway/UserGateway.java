package org.nhathm.domain.user.gateway;

import org.nhathm.domain.user.entity.User;

public interface UserGateway {

    /**
     * @param userId
     */
    User getById(String userId);

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
