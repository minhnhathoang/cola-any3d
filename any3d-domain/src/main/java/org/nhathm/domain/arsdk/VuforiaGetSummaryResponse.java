package org.nhathm.domain.arsdk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class VuforiaGetSummaryResponse extends VuforiaResponse {

    @JsonProperty("name")
    private String name;

    @JsonProperty("active_images")
    private int activeImages;

    @JsonProperty("inactive_images")
    private int inactiveImages;

    @JsonProperty("failed_images")
    private int failedImages;

    @JsonProperty("processing_images")
    private int processingImages;

    @JsonProperty("target_quota")
    private int targetQuota;

    @JsonProperty("request_quota")
    private int requestQuota;

    @JsonProperty("reco_threshold")
    private double recoThreshold;

    @JsonProperty("request_usage")
    private int requestUsage;

    @JsonProperty("total_recos")
    private int totalRecos;

    @JsonProperty("current_month_recos")
    private int currentMonthRecos;

    @JsonProperty("previous_month_recos")
    private int previousMonthRecos;
}
