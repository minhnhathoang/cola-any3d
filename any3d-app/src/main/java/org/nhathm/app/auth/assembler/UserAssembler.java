package org.nhathm.app.auth.assembler;

import domain.COAssembler;
import org.mapstruct.Mapper;
import org.nhathm.auth.dto.command.AuthRegisterCmd;
import org.nhathm.domain.user.dataobject.UserDO;
import org.nhathm.domain.user.entity.User;
import org.nhathm.user.dto.clientobject.UserCO;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = org.mapstruct.NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
public interface UserAssembler extends COAssembler<UserCO, User> {

    UserCO toCO(UserDO dataObject);

    User toEntity(AuthRegisterCmd cmd);
}
