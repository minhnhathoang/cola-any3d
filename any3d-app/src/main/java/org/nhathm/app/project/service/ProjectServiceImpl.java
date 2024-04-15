package org.nhathm.app.project.service;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.nhathm.app.project.executor.command.ContentCreatePresignedUploadUrlCmdExe;
import org.nhathm.app.project.executor.command.ProjectCreateCmdExe;
import org.nhathm.app.project.executor.command.ProjectDeleteCmdExe;
import org.nhathm.app.project.executor.query.ProjectListByOwnerQryExe;
import org.nhathm.content.dto.clientobject.ContentCO;
import org.nhathm.content.dto.command.query.ContentListByPageQry;
import org.nhathm.project.api.ContentCreatePresignedUploadUrlCO;
import org.nhathm.project.api.ProjectService;
import org.nhathm.project.dto.clientobject.ProjectCO;
import org.nhathm.project.dto.command.ContentCreatePresignedUploadUrlCmd;
import org.nhathm.project.dto.command.ProjectCreateCmd;
import org.nhathm.project.dto.command.ProjectDeleteCmd;
import org.nhathm.project.dto.command.query.ProjectListByOwnerQry;
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

    ProjectListByOwnerQryExe projectListByOwnerQryExe;

    ContentCreatePresignedUploadUrlCmdExe contentCreatePresignedUploadUrlCmdExe;

    @Override
    public Response createProject(ProjectCreateCmd cmd) {
        return projectCreateCmdExe.execute(cmd);
    }

    @Override
    public Response deleteProject(ProjectDeleteCmd cmd) {
        return projectDeleteCmdExe.execute(cmd);
    }

    @Override
    public SingleResponse<ContentCreatePresignedUploadUrlCO> createContentPresignedUploadUrl(
            ContentCreatePresignedUploadUrlCmd cmd) {
        return contentCreatePresignedUploadUrlCmdExe.execute(cmd);
    }

    @Override
    public MultiResponse<ProjectCO> getProjectListByOwnerId(ProjectListByOwnerQry qry) {
        return projectListByOwnerQryExe.execute(qry);
    }

    @Override
    public MultiResponse<ContentCO> getContentListByPageQry(ContentListByPageQry qry) {
        return null;
    }
}
