package org.nhathm.domain.project.gateway;

import org.nhathm.domain.project.entity.Project;

import java.util.List;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
public interface ProjectGateway {

    boolean isExistsById(Long id);

    void createProject(Project project);

    void update(Project project);

    void delete(String id);

    List<Project> getProjectListByOwnerId(Long ownerId);
}
