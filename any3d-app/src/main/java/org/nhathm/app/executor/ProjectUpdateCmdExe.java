package org.nhathm.app.executor;

import com.alibaba.cola.domain.DomainFactory;
import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import org.nhathm.app.assembler.ProjectAssembler;
import org.nhathm.domain.project.entity.Project;
import org.nhathm.domain.project.gateway.ProjectGateway;
import org.nhathm.dto.command.ProjectAddCmd;
import org.nhathm.dto.command.ProjectUpdateCmd;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class ProjectUpdateCmdExe {

    private final ProjectGateway projectGateway;

    public Response execute(ProjectUpdateCmd cmd) {
        Project project = DomainFactory.create(Project.class);
        project.setId(cmd.getId());
        project.setName(cmd.getName());
        project.setDescription(cmd.getDescription());
        projectGateway.update(project);
        return Response.buildSuccess();
    }
}
