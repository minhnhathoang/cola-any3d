package org.nhathm.domain.project.gateway;

import org.nhathm.domain.project.entity.Project;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
public interface ProjectGateway {

    void create(Project project);

    void update(Project project);
}
