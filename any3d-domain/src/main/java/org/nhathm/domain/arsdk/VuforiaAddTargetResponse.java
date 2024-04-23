package org.nhathm.domain.arsdk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class VuforiaAddTargetResponse extends VuforiaResponse {

    @JsonProperty("target_id")
    private String targetId;

    @Override
    public boolean isSuccess() {
        return "TargetCreated".equals(resultCode);
    }
}
