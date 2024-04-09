package org.nhathm.domain.project.gateway;

import org.nhathm.domain.project.entity.Project;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
public interface ProjectGateway {

    boolean isExistsById(Long id);

    boolean isExistsByUserIdAndName(Long userId, String name);

    void createProject(Project project);

    void update(Project project);

    void delete(String id);
}
