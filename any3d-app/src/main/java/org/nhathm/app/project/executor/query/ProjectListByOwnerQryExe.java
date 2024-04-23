package org.nhathm.app.project.executor.query;

import com.alibaba.cola.dto.MultiResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.app.project.assembler.ProjectAssembler;
import org.nhathm.domain.project.gateway.ProjectGateway;
import org.nhathm.dto.clientobject.ProjectCO;
import org.nhathm.dto.query.ProjectListByOwnerQry;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@RequiredArgsConstructor
@Component
public class ProjectListByOwnerQryExe {

    private final ProjectGateway projectGateway;

    private final ProjectAssembler projectAssembler;

    public MultiResponse<ProjectCO> execute(ProjectListByOwnerQry qry) {
        var projectList = projectGateway.getProjectListByOwnerId(qry.getOwnerId());
        System.out.println("ProjectListByOwnerQryExe: " + projectList);
        return MultiResponse.of(projectList.stream()
                .map(projectAssembler::toCO)
                .collect(Collectors.toList()));
    }
}
