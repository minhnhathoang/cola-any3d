package org.nhathm.domain.project.gateway;

import org.nhathm.domain.project.entity.Project;

import java.util.List;


public interface ProjectGateway {

    boolean isExistsById(String id);

    void createProject(Project project);

    void update(Project project);

    void delete(String id);

    List<Project> getProjectListByOwnerId(String ownerId);

    Project getById(String id);
}
