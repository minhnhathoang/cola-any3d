package org.nhathm.object.dto.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@EqualsAndHashCode(callSuper = false)
@Builder
@Data
public class ObjectListByProjectAndPageQry extends PageQuery {

    private String username;

    private String email;
}
