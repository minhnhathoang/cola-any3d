package org.nhathm.app.project.executor.query;

import com.alibaba.cola.dto.MultiResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.app.project.assembler.ProjectAssembler;
import org.nhathm.domain.project.gateway.ProjectGateway;
import org.nhathm.project.dto.clientobject.ProjectCO;
import org.nhathm.project.dto.command.query.ProjectListByOwnerQry;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Component
public class ProjectListByOwnerQryExe {

    private final ProjectGateway projectGateway;

    private final ProjectAssembler projectAssembler;

    public MultiResponse<ProjectCO> execute(ProjectListByOwnerQry qry) {
        var projectList = projectGateway.getProjectListByOwnerId(qry.getOwnerId());
        return MultiResponse.of(projectList.stream()
                .map(projectAssembler::toCO)
                .collect(Collectors.toList()));
    }
}
