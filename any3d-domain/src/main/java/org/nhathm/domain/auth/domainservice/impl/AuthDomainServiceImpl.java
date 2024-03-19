package org.nhathm.domain.auth.domainservice.impl;

import org.nhathm.domain.auth.domainservice.AuthDomainService;
import org.nhathm.domain.auth.gateway.AuthGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Service
public class AuthDomainServiceImpl implements AuthDomainService {

    @Autowired
    private AuthGateway authGateway;
}
