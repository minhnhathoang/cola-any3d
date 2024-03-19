package org.nhathm.domain.auth.gateway;

import org.nhathm.domain.user.entity.User;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
public interface AuthGateway {

    User login(String username, String password);
}
