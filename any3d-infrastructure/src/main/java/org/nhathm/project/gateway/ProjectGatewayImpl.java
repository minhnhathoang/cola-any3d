package org.nhathm.project.gateway;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.nhathm.domain.project.entity.Project;
import org.nhathm.domain.project.gateway.ProjectGateway;
import org.nhathm.project.database.ProjectConvertor;
import org.nhathm.project.database.ProjectRepository;
import org.springframework.stereotype.Component;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
@Component
public class ProjectGatewayImpl implements ProjectGateway {

    ProjectRepository projectRepository;

    ProjectConvertor projectConvertor;

    @Override
    public Project getById(String id) {
        var project = projectRepository.findById(id);
        return projectConvertor.toEntity(project.orElse(null));
    }

    @Override
    public List<Project> getProjectList() {
        return projectRepository.findAll().stream().map(projectConvertor::toEntity).toList();
    }

    @Override
    public void createProject(Project project) {
        projectRepository.save(projectConvertor.toDataObject(project));
    }

    @Override
    public void update(Project project) {
        projectRepository.save(projectConvertor.toDataObject(project));
    }

    @Override
    public void delete(String id) {
        projectRepository.deleteById(id);
    }
}
