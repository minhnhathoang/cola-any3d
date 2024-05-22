package org.nhathm.domain.userprofile.domainservice;

import org.nhathm.domain.arsdk.VuforiaAddTargetResponse;
import org.nhathm.domain.arsdk.VuforiaDeleteTargetResponse;
import org.nhathm.domain.arsdk.VuforiaGetSummaryResponse;
import org.nhathm.domain.arsdk.VuforiaGetTargetResponse;
import org.nhathm.domain.vuforia.entity.VuforiaKey;
import org.springframework.web.multipart.MultipartFile;

public interface VuforiaApiService {

    VuforiaGetSummaryResponse getSummary(VuforiaKey key);

    VuforiaAddTargetResponse addTarget(VuforiaKey key, MultipartFile file);

    VuforiaGetTargetResponse getTarget(VuforiaKey key, String targetId);

    VuforiaDeleteTargetResponse deleteTarget(VuforiaKey key, String targetId);

    boolean isValidKey(VuforiaKey key);
}
