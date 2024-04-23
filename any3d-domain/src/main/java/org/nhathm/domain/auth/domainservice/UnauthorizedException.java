package org.nhathm.domain.auth.domainservice;

import com.alibaba.cola.exception.BaseException;


public class UnauthorizedException extends BaseException {

    public UnauthorizedException(String errMessage) {
        super("AUTH-LOGIN-401", errMessage);
    }
}
