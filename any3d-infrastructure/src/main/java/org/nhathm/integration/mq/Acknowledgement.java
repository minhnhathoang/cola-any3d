package org.nhathm.integration.mq;

@FunctionalInterface
public interface Acknowledgement {

    void acknowledge();
}
