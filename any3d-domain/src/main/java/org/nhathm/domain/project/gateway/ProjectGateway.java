package org.nhathm.domain.project.gateway;

import org.nhathm.domain.project.entity.Project;

import java.util.List;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
public interface ProjectGateway {

    Project getById(String id);

    List<Project> getProjectList();

    void createProject(Project project);

    void update(Project project);

    void delete(String id);
}
