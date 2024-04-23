package org.nhathm.domain.imagetarget.gateway;


import org.nhathm.domain.imagetarget.entity.ImageTarget;

public interface ImageTargetGateway {

    void addImageTarget(ImageTarget imageTarget);

    void deleteImageTarget(String imageTargetId);
}
