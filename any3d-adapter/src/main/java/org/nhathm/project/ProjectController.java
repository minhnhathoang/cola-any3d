package org.nhathm.project;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import org.nhathm.APIConstant;
import org.nhathm.auth.gateway.SpringSecurityUtils;
import org.nhathm.content.dto.clientobject.ContentCO;
import org.nhathm.content.dto.command.query.ContentListByPageQry;
import org.nhathm.project.api.ProjectService;
import org.nhathm.project.dto.clientobject.ProjectCO;
import org.nhathm.project.dto.command.ContentCreatePresignedUploadUrlCmd;
import org.nhathm.project.dto.command.ProjectCreateCmd;
import org.nhathm.project.dto.command.ProjectDeleteCmd;
import org.nhathm.project.dto.command.query.ProjectListQry;
import org.springframework.web.bind.annotation.*;


/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@RequestMapping(APIConstant.WEB_API_PATH + "/projects")
@RestController
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public Response createProject(@RequestBody ProjectCreateCmd cmd) {
        cmd.setUserId(SpringSecurityUtils.getUserId());
        return projectService.createProject(cmd);
    }

    @GetMapping
    public MultiResponse<ProjectCO> getProjectList() {
        return projectService.getProjectList(ProjectListQry.builder().build());
    }

    @DeleteMapping("/{projectId}")
    public Response deleteProject(@PathVariable String projectId) {
        return projectService.deleteProject(ProjectDeleteCmd.builder().projectId(projectId).build());
    }

    @PostMapping("/contents/create-presigned-upload-url")
    public Response createPresignedUploadUrl(@RequestBody ContentCreatePresignedUploadUrlCmd cmd) {
        return projectService.createContentPresignedUploadUrl(cmd);
    }

    @GetMapping("/contents")
    public MultiResponse<ContentCO> contentListByPageQry(@ModelAttribute ContentListByPageQry qry) {
        return projectService.getContentListByPageQry(qry);
    }
}
