package org.nhathm.app.executor;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.app.assembler.ProjectAssembler;
import org.nhathm.domain.project.gateway.ProjectGateway;
import org.nhathm.dto.clientobject.ProjectCO;
import org.nhathm.dto.query.ProjectGetByIdQry;
import org.nhathm.dto.query.ProjectListByOwnerQry;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@RequiredArgsConstructor
@Component
public class ProjectGetByIdQryExe {

    private final ProjectGateway projectGateway;

    private final ProjectAssembler projectAssembler;

    public SingleResponse<ProjectCO> execute(ProjectGetByIdQry qry) {
        var projectList = projectGateway.getById(qry.getProjectId());
        System.out.println("ProjectListByOwnerQryExe: " + projectList);
        return SingleResponse.of(projectAssembler.toCO(projectList));
    }
}
