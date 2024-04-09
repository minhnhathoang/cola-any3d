package org.nhathm.domain.objectstorage.mq;

import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Component
public class ObjectStorageProducer {

    public void send() {
        System.out.println("send");
    }
}
