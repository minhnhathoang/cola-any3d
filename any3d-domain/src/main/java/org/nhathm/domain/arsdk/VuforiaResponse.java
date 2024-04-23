package org.nhathm.domain.arsdk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author nhathm
 */
@Data
public class VuforiaResponse {

    @JsonProperty("result_code")
    protected String resultCode;

    @JsonProperty("transaction_id")
    protected String transactionId;

    public boolean isSuccess() {
        return "Success".equals(resultCode);
    }
}
