package org.nhathm.domain.user.database;

import domain.EntityConvertor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.nhathm.domain.user.dataobject.UserDO;
import org.nhathm.domain.user.entity.User;
import org.nhathm.domain.user.entity.UserDetails;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserConvertor extends EntityConvertor<User, UserDO> {

    @Mapping(source = "hashedPassword", target = "password")
    UserDetails toUserDetails(UserDO userDO);

    @Mapping(target = "hashedPassword", source = "password")
    UserDO toDataObject(User user);

    @Mapping(target = "password", source = "hashedPassword")
    User toEntity(UserDO userDO);
}