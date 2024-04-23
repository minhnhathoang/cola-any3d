package org.nhathm.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import org.nhathm.dto.clientobject.ProjectCO;
import org.nhathm.dto.command.ProjectAddCmd;
import org.nhathm.dto.command.ProjectDeleteCmd;
import org.nhathm.dto.query.ProjectListByOwnerQry;


public interface ProjectService {

    Response addProject(ProjectAddCmd cmd);

    Response deleteProject(ProjectDeleteCmd cmd);


    MultiResponse<ProjectCO> getProjectListByOwnerId(ProjectListByOwnerQry qry);
}
