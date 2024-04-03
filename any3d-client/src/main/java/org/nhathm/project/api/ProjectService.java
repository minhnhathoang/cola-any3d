package org.nhathm.project.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import org.nhathm.project.dto.clientobject.ContentCO;
import org.nhathm.project.dto.clientobject.ProjectCO;
import org.nhathm.project.dto.command.ProjectCreateCmd;
import org.nhathm.project.dto.command.ProjectDeleteCmd;
import org.nhathm.project.dto.command.query.ContentListByPageQry;
import org.nhathm.project.dto.command.query.ProjectGetPresignedUploadUrlQry;
import org.nhathm.project.dto.command.query.ProjectListQry;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
public interface ProjectService {

    Response createProject(ProjectCreateCmd cmd);

    Response deleteProject(ProjectDeleteCmd cmd);

    SingleResponse<ProjectGetPresignedUploadUrlCO> getPresignedUploadUrl(ProjectGetPresignedUploadUrlQry qry);

    MultiResponse<ProjectCO> getProjectList(ProjectListQry qry);

    MultiResponse<ContentCO> getContentListByPageQry(ContentListByPageQry qry);
}
