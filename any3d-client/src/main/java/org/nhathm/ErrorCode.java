package org.nhathm;

import com.alibaba.cola.exception.BizException;

public enum ErrorCode {
    B_AUTH_Unauthorized("AUTH-401", "Unauthorized"),
    B_USER_UsernameAlreadyExist("USER-409", "Username already exists"),
    B_USER_UserNotFound("USER-404", "User not found");

    private final String errCode;
    private final String errDesc;

    private ErrorCode(String errCode, String errDesc) {
        this.errCode = errCode;
        this.errDesc = errDesc;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrDesc() {
        return errDesc;
    }

    public BizException toBizException() {
        return new BizException(this.errCode, this.errDesc);
    }
}
