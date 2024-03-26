package org.nhathm.object.dto.clientobject;

import lombok.Builder;
import lombok.Data;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Data
@Builder
public class PresignedUrlCO {

    private String url;
    private long expiry;
}
