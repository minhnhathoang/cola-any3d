package org.nhathm.integration.mq;

public interface MessageSendCallback {

    void onSuccess(MessageSendResult result);

    void onFailed(Throwable e);
}
