package org.nhathm.domain.vuforia.gateway;


import org.nhathm.domain.vuforia.entity.VuforiaKey;

public interface VuforiaGateway {

    void addKey(VuforiaKey key);

    void updateKey(VuforiaKey key);

    VuforiaKey findKeyByProjectId(String projectId);
}
