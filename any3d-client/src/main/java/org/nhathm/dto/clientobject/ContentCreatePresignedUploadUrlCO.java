package org.nhathm.dto.clientobject;

import com.alibaba.cola.dto.ClientObject;
import lombok.Data;


@Data
public class ContentCreatePresignedUploadUrlCO extends ClientObject {

    private String contentId;

    private String presignedUrl;
}
