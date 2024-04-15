package org.nhathm.domain.project.gateway;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.nhathm.domain.project.database.ProjectConvertor;
import org.nhathm.domain.project.database.ProjectMapper;
import org.nhathm.domain.project.dataobject.ProjectDO;
import org.nhathm.domain.project.entity.Project;
import org.nhathm.event.DomainEventPublisher;
import org.nhathm.project.dto.event.ProjectCreatedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Component
public class ProjectGatewayImpl
        extends ServiceImpl<ProjectMapper, ProjectDO> implements ProjectGateway {

    private final ProjectConvertor projectConvertor;

    private final DomainEventPublisher domainEventPublisher;

    @Override
    public boolean isExistsById(Long id) {
        return this.lambdaQuery()
                .eq(ProjectDO::getId, id)
                .count() > 0;
    }

    @Override
    public void createProject(Project project) {
        ProjectDO projectDO = projectConvertor.toDataObject(project);
        this.save(projectDO);

        // Publish event
        ProjectCreatedEvent event = new ProjectCreatedEvent();
        event.setProjectId(projectDO.getId());
        domainEventPublisher.publishProjectEvent(event);
    }

    @Override
    public void update(Project project) {
//        projectRepository.save(projectConvertor.toDataObject(project));
    }

    @Override
    public void delete(String id) {
//        projectRepository.deleteById(id);
    }

    @Override
    public List<Project> getProjectListByOwnerId(Long ownerId) {
        var projectDOList = this.lambdaQuery()
                .eq(ProjectDO::getOwnerId, ownerId)
                .list();
        var projectList = new ArrayList<Project>();
        for (ProjectDO projectDO : projectDOList) {
            projectList.add(projectConvertor.toEntity(projectDO));
        }
        return projectList;
    }

}
