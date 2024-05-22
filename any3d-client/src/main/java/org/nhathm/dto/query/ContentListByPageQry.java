package org.nhathm.dto.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@Data
public class ContentListByPageQry extends PageQuery {

    private String projectId;

    private String searchKey = "";
}
