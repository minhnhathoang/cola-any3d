package org.nhathm.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import org.nhathm.dto.clientobject.ProjectCO;
import org.nhathm.dto.command.ProjectAddCmd;
import org.nhathm.dto.command.ProjectDeleteCmd;
import org.nhathm.dto.command.ProjectUpdateCmd;
import org.nhathm.dto.query.ProjectGetByIdQry;
import org.nhathm.dto.query.ProjectListByOwnerQry;


public interface ProjectService {

    Response addProject(ProjectAddCmd cmd);

    Response updateProject(ProjectUpdateCmd cmd);

    Response deleteProject(ProjectDeleteCmd cmd);

    SingleResponse<ProjectCO> getById(ProjectGetByIdQry qry);

    MultiResponse<ProjectCO> getProjectListByOwnerId(ProjectListByOwnerQry qry);
}
