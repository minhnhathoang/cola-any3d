package org.nhathm.controller;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.APIConstant;
import org.nhathm.api.ProjectService;
import org.nhathm.common.SpringSecurityUtils;
import org.nhathm.dto.clientobject.ProjectCO;
import org.nhathm.dto.command.ProjectAddCmd;
import org.nhathm.dto.command.ProjectDeleteCmd;
import org.nhathm.dto.command.ProjectUpdateCmd;
import org.nhathm.dto.query.ProjectGetByIdQry;
import org.nhathm.dto.query.ProjectListByOwnerQry;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping(APIConstant.WEB_API_PATH + "/projects")
@RestController
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public Response addProject(@RequestBody ProjectAddCmd cmd) {
        cmd.setOwnerId(SpringSecurityUtils.getUserId());
        return projectService.addProject(cmd);
    }

    @PutMapping("/{id}")
    public Response updateProject(@PathVariable String id, @RequestBody ProjectUpdateCmd cmd) {
        cmd.setId(id);
        return projectService.updateProject(cmd);
    }

    @DeleteMapping("/{id}")
    public Response deleteProject(@PathVariable String id) {
        return projectService.deleteProject(ProjectDeleteCmd.builder().projectId(id).build());
    }

    @GetMapping("/{id}")
    public SingleResponse<ProjectCO> getProjectById(@PathVariable String id) {
        return projectService.getById(ProjectGetByIdQry.builder().projectId(id).build());
    }

    @GetMapping
    public MultiResponse<ProjectCO> getProjectListByOwnerId() {
        return projectService.getProjectListByOwnerId(ProjectListByOwnerQry
                .builder()
                .ownerId(SpringSecurityUtils.getUserId())
                .build());
    }
}
