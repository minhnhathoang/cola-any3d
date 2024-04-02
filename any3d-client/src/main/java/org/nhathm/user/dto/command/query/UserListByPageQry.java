package org.nhathm.user.dto.command.query;

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
public class UserListByPageQry extends PageQuery {

    private String username;

    private String email;
}
