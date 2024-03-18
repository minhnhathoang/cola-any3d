package org.nhathm.dto.data;

import com.alibaba.cola.exception.BizException;

public enum ErrorCode {

    B_USER_userNotFound("USER-404", "User not found"),
    ;

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
