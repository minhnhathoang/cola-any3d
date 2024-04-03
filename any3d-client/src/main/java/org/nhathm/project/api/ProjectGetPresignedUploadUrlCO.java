package org.nhathm.project.api;

import com.alibaba.cola.dto.ClientObject;
import domain.security.common.AccessToken;
import lombok.Builder;
import lombok.Data;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Builder
@Data
public class ProjectGetPresignedUploadUrlCO extends ClientObject {

    private String username;

    private AccessToken accessToken;
}
