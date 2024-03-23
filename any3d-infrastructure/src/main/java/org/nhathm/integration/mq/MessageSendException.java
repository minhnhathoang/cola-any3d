package org.nhathm.integration.mq;

import com.alibaba.cola.exception.BaseException;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
public class MessageSendException extends BaseException {
    public MessageSendException(String errMessage) {
        super(errMessage);
    }

    public MessageSendException(String errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public MessageSendException(String errMessage, Throwable e) {
        super(errMessage, e);
    }

    public MessageSendException(String errCode, String errMessage, Throwable e) {
        super(errCode, errMessage, e);
    }
}
