package org.nhathm.app.assembler;

import domain.COAssembler;
import org.mapstruct.Mapper;
import org.nhathm.domain.user.entity.User;
import org.nhathm.dto.clientobject.UserCO;
import org.nhathm.dto.command.AuthRegisterCmd;


@Mapper(componentModel = "spring",
        nullValueCheckStrategy = org.mapstruct.NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
public interface UserAssembler extends COAssembler<UserCO, User> {

    User toEntity(AuthRegisterCmd cmd);
}
