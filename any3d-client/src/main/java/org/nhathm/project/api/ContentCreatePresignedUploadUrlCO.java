package org.nhathm.project.api;

import com.alibaba.cola.dto.ClientObject;
import lombok.Builder;
import lombok.Data;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Data
@Builder
public class ContentCreatePresignedUploadUrlCO extends ClientObject {

    private String contentId;

    private String presignedUrl;
}
