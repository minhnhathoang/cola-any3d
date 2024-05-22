package org.nhathm.app.executor;

import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import org.nhathm.common.SpringSecurityUtils;
import org.nhathm.domain.project.gateway.ProjectGateway;
import org.nhathm.dto.command.ProjectDeleteCmd;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class ProjectDeleteCmdExe {

    private final ProjectGateway projectGateway;

    public Response execute(ProjectDeleteCmd cmd) {
        projectGateway.delete(cmd.getProjectId(), SpringSecurityUtils.getUserId());
        return Response.buildSuccess();
    }
}
