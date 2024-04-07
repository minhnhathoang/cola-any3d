package org.nhathm.domain.user.database;

import domain.EntityConvertor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.nhathm.domain.user.dataobject.UserDO;
import org.nhathm.domain.user.entity.User;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserConvertor extends EntityConvertor<User, UserDO> {

    @Mapping(target = "hashedPassword", source = "password")
    UserDO toDataObject(User user);

    @Mapping(target = "password", source = "hashedPassword")
    User toEntity(UserDO userDO);
}