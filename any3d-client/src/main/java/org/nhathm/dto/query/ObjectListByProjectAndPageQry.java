package org.nhathm.dto.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = false)
@Builder
@Data
public class ObjectListByProjectAndPageQry extends PageQuery {

    private String username;

    private String email;
}
