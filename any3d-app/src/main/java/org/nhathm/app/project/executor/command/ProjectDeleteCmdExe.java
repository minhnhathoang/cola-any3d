package org.nhathm.app.project.executor.command;

import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import org.nhathm.domain.project.gateway.ProjectGateway;
import org.nhathm.project.dto.command.ProjectDeleteCmd;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Component
public class ProjectDeleteCmdExe {

    private final ProjectGateway projectGateway;

    public Response execute(ProjectDeleteCmd cmd) {
        // TODO:
        return null;
    }
}
