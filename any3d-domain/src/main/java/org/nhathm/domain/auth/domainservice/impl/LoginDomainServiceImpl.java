package org.nhathm.domain.auth.domainservice.impl;

import org.nhathm.domain.auth.domainservice.LoginDomainService;

import org.nhathm.domain.auth.gateway.LoginGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Service
public class LoginDomainServiceImpl implements LoginDomainService {

    @Autowired
    private LoginGateway loginGateway;
}
