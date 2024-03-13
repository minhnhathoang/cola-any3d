package org.nhathm.user.dto.command;

import lombok.Builder;
import lombok.Data;
import org.nhathm.CommonCommand;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Data
@Builder
public class UserDeleteCmd extends CommonCommand {

    private String userId;
}
