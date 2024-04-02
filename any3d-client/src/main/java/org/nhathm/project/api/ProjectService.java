package org.nhathm.project.api;

import com.alibaba.cola.dto.Response;
import org.nhathm.project.dto.command.ProjectCreateCmd;
import org.nhathm.project.dto.command.ProjectDeleteCmd;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
public interface ProjectService {

    Response createProject(ProjectCreateCmd cmd);

    Response deleteProject(ProjectDeleteCmd cmd);
}
