package org.nhathm.domain.userprofile.domainservice;

import org.nhathm.domain.arsdk.VuforiaAddTargetResponse;
import org.nhathm.domain.vuforia.entity.VuforiaKey;
import org.springframework.web.multipart.MultipartFile;

public interface VuforiaApiService {

    VuforiaAddTargetResponse addTarget(VuforiaKey key, MultipartFile file);
}
