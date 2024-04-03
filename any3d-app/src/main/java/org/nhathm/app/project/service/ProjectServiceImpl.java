package org.nhathm.app.project.service;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.nhathm.app.project.executor.command.ProjectCreateCmdExe;
import org.nhathm.app.project.executor.command.ProjectDeleteCmdExe;
import org.nhathm.project.api.ProjectGetPresignedUploadUrlCO;
import org.nhathm.project.api.ProjectService;
import org.nhathm.project.dto.clientobject.ContentCO;
import org.nhathm.project.dto.clientobject.ProjectCO;
import org.nhathm.project.dto.command.ProjectCreateCmd;
import org.nhathm.project.dto.command.ProjectDeleteCmd;
import org.nhathm.project.dto.command.query.ContentListByPageQry;
import org.nhathm.project.dto.command.query.ProjectGetPresignedUploadUrlQry;
import org.nhathm.project.dto.command.query.ProjectListQry;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProjectServiceImpl implements ProjectService {

    ProjectCreateCmdExe projectCreateCmdExe;

    ProjectDeleteCmdExe projectDeleteCmdExe;

    @Override
    public Response createProject(ProjectCreateCmd cmd) {
        return projectCreateCmdExe.execute(cmd);
    }

    @Override
    public Response deleteProject(ProjectDeleteCmd cmd) {
        return projectDeleteCmdExe.execute(cmd);
    }

    @Override
    public SingleResponse<ProjectGetPresignedUploadUrlCO> getPresignedUploadUrl(ProjectGetPresignedUploadUrlQry qry) {

        return null;
    }

    @Override
    public MultiResponse<ProjectCO> getProjectList(ProjectListQry qry) {
        return null;
    }

    @Override
    public MultiResponse<ContentCO> getContentListByPageQry(ContentListByPageQry qry) {
        return null;
    }
}
