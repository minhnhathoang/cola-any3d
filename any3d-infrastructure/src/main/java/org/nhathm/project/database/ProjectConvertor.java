package org.nhathm.project.database;

import domain.EntityConvertor;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.nhathm.domain.project.entity.Project;
import org.nhathm.project.dataobject.ProjectDO;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProjectConvertor extends EntityConvertor<Project, ProjectDO> {

}
