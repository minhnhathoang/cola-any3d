package org.nhathm.app.assembler;

import domain.COAssembler;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.nhathm.domain.content.entity.Content;
import org.nhathm.domain.project.entity.Project;
import org.nhathm.domain.user.entity.User;
import org.nhathm.dto.clientobject.ContentCO;
import org.nhathm.dto.clientobject.ProjectCO;
import org.nhathm.dto.command.ProjectAddCmd;


@Mapper(componentModel = "spring",
        nullValueCheckStrategy = org.mapstruct.NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface ContentAssembler extends COAssembler<ContentCO, Content> {

}
