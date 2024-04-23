package org.nhathm.dto.clientobject;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PresignedUrlCO {

    private String url;
    private long expiry;
}
