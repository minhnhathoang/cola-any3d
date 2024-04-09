package org.nhathm.domain.project.gateway;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.nhathm.domain.project.database.ProjectConvertor;
import org.nhathm.domain.project.database.ProjectMapper;
import org.nhathm.domain.project.dataobject.ProjectDO;
import org.nhathm.domain.project.entity.Project;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@Component
public class ProjectGatewayImpl
        extends ServiceImpl<ProjectMapper, ProjectDO> implements ProjectGateway {


    private final ProjectConvertor projectConvertor;

    @Override
    public boolean isExistsById(Long id) {
        return this.lambdaQuery()
                .eq(ProjectDO::getId, id)
                .count() > 0;
    }

    @Override
    public boolean isExistsByUserIdAndName(Long userId, String name) {
        return this.lambdaQuery()
                .eq(ProjectDO::getUserId, userId)
                .eq(ProjectDO::getName, name)
                .count() > 0;
    }

    @Override
    public void createProject(Project project) {
        this.save(projectConvertor.toDataObject(project));
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
