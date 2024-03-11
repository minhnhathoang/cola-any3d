package org.nhathm.domain.user.gateway;

import org.nhathm.domain.user.entity.User;

public interface UserGateway {

    User getById(String userId);
}
