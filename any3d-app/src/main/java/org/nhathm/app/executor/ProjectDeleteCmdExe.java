package org.nhathm.app.executor;

import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import org.nhathm.domain.project.gateway.ProjectGateway;
import org.nhathm.dto.command.ProjectDeleteCmd;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class ProjectDeleteCmdExe {

    private final ProjectGateway projectGateway;

    public Response execute(ProjectDeleteCmd cmd) {
        System.out.println("Delete project: " + cmd.getProjectId());
        projectGateway.delete(cmd.getProjectId());
        return Response.buildSuccess();
    }
}
