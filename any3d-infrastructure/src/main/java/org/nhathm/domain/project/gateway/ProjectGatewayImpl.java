package org.nhathm.domain.project.gateway;

import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.nhathm.domain.project.database.ProjectConvertor;
import org.nhathm.domain.project.database.ProjectMapper;
import org.nhathm.domain.project.dataobject.ProjectDO;
import org.nhathm.domain.project.entity.Project;
import org.nhathm.dto.domainevent.ProjectAddedEvent;
import org.nhathm.dto.domainevent.ProjectDeletedEvent;
import org.nhathm.dto.domainevent.ProjectUpdatedEvent;
import org.nhathm.event.EventPublisher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Component
@Log4j2
public class ProjectGatewayImpl
        extends MPJBaseServiceImpl<ProjectMapper, ProjectDO> implements ProjectGateway {

    private final ProjectConvertor projectConvertor;

    private final EventPublisher eventPublisher;

    @Override
    public void createProject(Project project) {
        ProjectDO projectDO = projectConvertor.toDataObject(project);
        this.save(projectDO);

        // publish event
        ProjectAddedEvent event = new ProjectAddedEvent();
        event.setProjectId(projectDO.getId());
        eventPublisher.publish(event);
    }

    @Override
    public void update(Project project) {
        ProjectDO projectDO = projectConvertor.toDataObject(project);
        this.baseMapper.updateById(projectDO);

        // publish event
        ProjectUpdatedEvent event = new ProjectUpdatedEvent();
        event.setProjectId(projectDO.getId());
        eventPublisher.publish(event);
    }

    @Override
    public void delete(String id, String userId) {
        ProjectDO projectDO = this.baseMapper.getById(id);
        this.removeById(id);

        // publish event
        ProjectDeletedEvent event = new ProjectDeletedEvent();
        event.setId(id);
        event.setName(projectDO.getName());
        event.setDeletedBy(userId);
        eventPublisher.publish(event);
    }

    @Override
    public List<Project> getProjectListByOwnerId(String ownerId) {
        List<Project> projectList = new ArrayList<>();
        List<ProjectDO> projectDOList = this.baseMapper.selectByOwnerId(ownerId);
        for (ProjectDO projectDO : projectDOList) {
            projectList.add(projectConvertor.toEntity(projectDO));
        }
        return projectList;
    }

    @Override
    public Project getById(String id) {
        ProjectDO projectDO = this.baseMapper.getById(id);
        log.info("projectDO: {}", projectDO);
        return projectConvertor.toEntity(projectDO);
    }
}
