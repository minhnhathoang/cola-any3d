package org.nhathm.domain.auth.gateway;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
public interface LoginGateway {

    boolean login(String username, String password);
}
