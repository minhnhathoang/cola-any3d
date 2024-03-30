package util.error;

import com.alibaba.cola.exception.BaseException;

public class ThirdServiceException extends BaseException {

    public ThirdServiceException(String errCode, Throwable ex) {
        super(errCode, ex);
    }

    public ThirdServiceException(String errCode, String errMessage) {
        super(errCode, errMessage);
    }
}
