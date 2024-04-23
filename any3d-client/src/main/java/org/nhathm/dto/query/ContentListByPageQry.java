package org.nhathm.dto.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ContentListByPageQry extends PageQuery {

    private String projectId;
}
