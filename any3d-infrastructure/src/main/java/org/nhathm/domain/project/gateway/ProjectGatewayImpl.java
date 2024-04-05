package org.nhathm.domain.project.gateway;

import lombok.RequiredArgsConstructor;
import org.nhathm.domain.project.database.ProjectConvertor;
import org.nhathm.domain.project.entity.Project;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Component
public class ProjectGatewayImpl implements ProjectGateway {


    private final ProjectConvertor projectConvertor;

    @Override
    public Project getById(String id) {
        return null;
    }

    @Override
    public List<Project> getProjectList() {
        return null;
    }

    @Override
    public void createProject(Project project) {
//        projectRepository.save(projectConvertor.toDataObject(project));
    }

    @Override
    public void update(Project project) {
//        projectRepository.save(projectConvertor.toDataObject(project));
    }

    @Override
    public void delete(String id) {
//        projectRepository.deleteById(id);
    }
}
