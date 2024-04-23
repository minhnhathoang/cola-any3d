package org.nhathm.app.assembler;

import domain.COAssembler;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.nhathm.domain.project.entity.Project;
import org.nhathm.domain.user.entity.User;
import org.nhathm.dto.clientobject.ProjectCO;
import org.nhathm.dto.command.ProjectAddCmd;


@Mapper(componentModel = "spring",
        nullValueCheckStrategy = org.mapstruct.NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE,
        uses = {UserAssembler.class}
)
public interface ProjectAssembler extends COAssembler<ProjectCO, Project> {

    @Mapping(target = "metadata", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "owner", source = "ownerId", qualifiedByName = "project_ownerFromOwnerId")
    Project toEntity(ProjectAddCmd cmd);

    ProjectCO toCO(Project project);

    @Named("project_ownerFromOwnerId")
    default User ownerFromOwnerId(String ownerId) {
        return User.builder().id(ownerId).build();
    }
}
