package org.nhathm.app.project.service;

import com.alibaba.cola.dto.Response;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.nhathm.app.project.executor.command.ProjectCreateCmdExe;
import org.nhathm.app.project.executor.command.ProjectDeleteCmdExe;
import org.nhathm.project.api.ProjectService;
import org.nhathm.project.dto.command.ProjectCreateCmd;
import org.nhathm.project.dto.command.ProjectDeleteCmd;
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
}
