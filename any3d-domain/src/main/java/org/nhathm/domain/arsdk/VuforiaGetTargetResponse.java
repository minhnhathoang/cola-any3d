package org.nhathm.domain.arsdk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data

public class VuforiaGetTargetResponse extends VuforiaResponse {

    @JsonProperty("target_record")
    private TargetRecord targetRecord;

    @Data
    static class TargetRecord {
        @JsonProperty("target_id")
        private String targetId;

        @JsonProperty("active_flag")
        private boolean activeFlag;

        private String name;

        private int width;

        @JsonProperty("tracking_rating")
        private int trackingRating;

        @JsonProperty("reco_rating")
        private String recoRating;
    }
}
