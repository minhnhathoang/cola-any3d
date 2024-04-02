package org.nhathm.project.database;

import org.nhathm.project.dataobject.ProjectDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Repository
public interface ProjectRepository extends JpaRepository<ProjectDO, String> {

}
