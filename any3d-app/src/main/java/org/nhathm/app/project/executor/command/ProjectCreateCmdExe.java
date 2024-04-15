package org.nhathm.app.project.executor.command;

import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import org.nhathm.app.project.assembler.ProjectAssembler;
import org.nhathm.domain.project.entity.Project;
import org.nhathm.domain.project.gateway.ProjectGateway;
import org.nhathm.project.dto.command.ProjectCreateCmd;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Component
public class ProjectCreateCmdExe {

    private final ProjectGateway projectGateway;

    private final ProjectAssembler projectAssembler;

    public Response execute(ProjectCreateCmd cmd) {
        Project project = projectAssembler.toEntity(cmd);
        projectGateway.createProject(project);
        return Response.buildSuccess();
    }
}
