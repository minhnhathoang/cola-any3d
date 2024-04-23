package org.nhathm.app.serviceimpl;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.nhathm.api.ProjectService;
import org.nhathm.app.executor.ProjectCreateCmdExe;
import org.nhathm.app.executor.ProjectDeleteCmdExe;
import org.nhathm.app.executor.ProjectListByOwnerQryExe;
import org.nhathm.dto.clientobject.ProjectCO;
import org.nhathm.dto.command.ProjectAddCmd;
import org.nhathm.dto.command.ProjectDeleteCmd;
import org.nhathm.dto.query.ProjectListByOwnerQry;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProjectServiceImpl implements ProjectService {

    ProjectCreateCmdExe projectCreateCmdExe;

    ProjectDeleteCmdExe projectDeleteCmdExe;

    ProjectListByOwnerQryExe projectListByOwnerQryExe;

    @Override
    public Response addProject(ProjectAddCmd cmd) {
        return projectCreateCmdExe.execute(cmd);
    }

    @Override
    public Response deleteProject(ProjectDeleteCmd cmd) {
        return projectDeleteCmdExe.execute(cmd);
    }

    @Override
    public MultiResponse<ProjectCO> getProjectListByOwnerId(ProjectListByOwnerQry qry) {
        return projectListByOwnerQryExe.execute(qry);
    }
}
