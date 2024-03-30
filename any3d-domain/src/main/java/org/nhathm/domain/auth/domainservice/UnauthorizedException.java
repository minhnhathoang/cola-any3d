package org.nhathm.domain.auth.domainservice;

import com.alibaba.cola.exception.BaseException;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
public class UnauthorizedException extends BaseException {

    public UnauthorizedException(String errMessage) {
        super("AUTH-LOGIN-401", errMessage);
    }
}
