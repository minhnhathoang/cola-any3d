package org.nhathm.domain.project.gateway;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.nhathm.domain.project.database.ProjectConvertor;
import org.nhathm.domain.project.database.ProjectMapper;
import org.nhathm.domain.project.dataobject.ProjectDO;
import org.nhathm.domain.project.entity.Project;
import org.nhathm.dto.domainevent.ProjectCreatedEvent;
import org.nhathm.event.DomainEventPublisher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Component
public class ProjectGatewayImpl
        extends ServiceImpl<ProjectMapper, ProjectDO> implements ProjectGateway {

    private final ProjectConvertor projectConvertor;

    private final DomainEventPublisher domainEventPublisher;

    @Override
    public boolean isExistsById(String id) {
        return this.lambdaQuery()
                .eq(ProjectDO::getId, id)
                .count() > 0;
    }

    @Override
    public void createProject(Project project) {
        ProjectDO projectDO = projectConvertor.toDataObject(project);
        this.save(projectDO);

        // publish event
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
        this.baseMapper.deleteById(id);
    }

    @Override
    public List<Project> getProjectListByOwnerId(String ownerId) {
        List<ProjectDO> projectDOList = this.lambdaQuery()
                .eq(ProjectDO::getOwnerId, ownerId)
                .list();
        List<Project> projectList = new ArrayList<>();
        for (ProjectDO projectDO : projectDOList) {
            projectList.add(projectConvertor.lazyFetchToEntity(projectDO));
        }
        return projectList;
    }

    @Override
    public Project getById(String id) {
        ProjectDO projectDO = this.baseMapper.getById(id);
        return projectConvertor.lazyFetchToEntity(projectDO);
    }
}
