package org.nhathm.domain.project.gateway;

import org.nhathm.domain.project.entity.Project;

import java.util.List;


public interface ProjectGateway {

    void createProject(Project project);

    void update(Project project);

    /*
     * deleted by userId
     */
    void delete(String id, String userId);

    List<Project> getProjectListByOwnerId(String ownerId);

    Project getById(String id);
}
