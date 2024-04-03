package org.nhathm.project.dto.command.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Builder;
import lombok.Data;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Data
@Builder
public class ContentListByPageQry extends PageQuery {

    private String projectId;
}
