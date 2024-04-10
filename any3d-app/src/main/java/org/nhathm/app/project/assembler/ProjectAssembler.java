package org.nhathm.app.project.assembler;

import domain.COAssembler;
import org.mapstruct.Mapper;
import org.nhathm.domain.project.entity.Project;
import org.nhathm.project.dto.clientobject.ProjectCO;
import org.nhathm.project.dto.command.ProjectCreateCmd;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = org.mapstruct.NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
public interface ProjectAssembler extends COAssembler<ProjectCO, Project> {

    Project toEntity(ProjectCreateCmd cmd);
}
