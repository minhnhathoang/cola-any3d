package org.nhathm.project;

import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import org.nhathm.APIConstant;
import org.nhathm.project.api.ProjectService;
import org.nhathm.project.dto.command.ProjectCreateCmd;
import org.nhathm.project.dto.command.ProjectDeleteCmd;
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
        return projectService.createProject(cmd);
    }

    @DeleteMapping("/{id}")
    public Response deleteProject(@PathVariable String id) {
        return projectService.deleteProject(ProjectDeleteCmd.builder().projectId(id).build());
    }
}
